## elasticsearch简介

ElasticSearch是一个基于Lucene的搜索服务器。它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。Elasticsearch是用Java语言开发的，并作为Apache许可条款下的开放源码发布，是一种流行的企业级搜索引擎。ElasticSearch用于云计算中，能够达到实时搜索，稳定，可靠，快速，安装使用方便。（来自百度百科）

### 核心概念

- 索引（Index）：相当于关系数据库中的一个数据库实例，用于定义文档类型的存储；在同一个索引中，同一个字段只能定义一个数据类型；
- 文档类型（Type）：相当于关系表，用于描述文档中的各个字段的定义；不同的文档类型，能够存储不同的字段，服务于不同的查询请求；
- 文档（Document）：文档是可被索引的基础逻辑单元，相当于关系表的数据行，存储数据的载体，包含一个或多个存有数据的字段。
    - 字段（Field）：文档的一个Key/Value对，字段相当于关系数据库中列的概念；
    - 词（Term）：表示文本中的一个单词；
    - 标记（Token）：表示在字段中出现的词，由该词的文本、偏移量（开始和结束）以及类型组成；
    
Elasticsearch与关系型数据库对比：

|ElasticSearch|RDBMS|
|:----|:----|
|索引（index）|数据库（database）|
|类型（type）|表（table）|
|文档（document）|行（row）|
|字段（field）| 列（column）|
|映射（mapping）|表结构|
|全文索引|索引|
|查询DSL|SQL|
|GET|select|
|PUT/POST|update|
|DELETE|delete|

分片: ElasticSearch将索引中的数据进行切分成多个分片（shard），每个分片存储这个索引的一部分数据，分布在不同节点上。
当需要查询索引时，ElasticSearch将查询发送到每个相关分片，之后将查询结果合并，这个过程对ElasticSearch应用来说是透明的，用户感知不到分片的存在。 
一个索引的分片一经指定，不再修改。在定义mapping时通过指定设置number_of_shards参数指定分片数。

副本: 分片全称是主分片，简称为分片。主分片是相对于副本来说的，副本是对主分片的一个或多个复制版本（或称拷贝），
这些复制版本（拷贝）可以称为复制分片，可以直接称之为副本。当主分片丢失时，集群可以将一个副本升级为新的主分片。
在定义mapping时通过指定设置number_of_replicas参数指定副本数。

路由机制: Elasticsearch的路由机制与其分片机制有着直接的关系。Elasticsearch的路由机制就是通过哈希算法，将具有相同哈希值的文档放到同一个
主分片中。Elasticsearch的默认算法就是以文档的ID的哈希值作为依据将文档放到相应的主分片上，这种算法可以保证所有的数据在所有分片上均匀分布。
用户也可以自行指定路由。

### 索引

索引命名限制：
- 仅限使用小写字母
- 不能包含\、/、 *、?、"、<、>、|、#以及空格等特殊符号
- 从7.0版本开始不再包含冒号
- 不能以-、_或+开头
- 不能超过255个字节（注意它是字节，因此多字节字符将计入255个限制）
- 索引名不能重复

正确的索引名如：student、student_course、student+order、student.order、123、student123
错误的索引名如：Student、_student、student:order

### 映射Mapping

Elasticsearch使用映射来定义一个索引中的文档的存储方式，在Elasticsearch 7.x版本中，映射和索引是一对一的关系。根据不同的创建方式，映射又被分为静态映射和动态映射。

- 静态映射: 是指用户在创建索引时手动指定映射，定义各个字段的属性。
- 动态映射: 当用户向一个不存在的索引添加文档时，Elasticsearch会根据文档字段自动生成一个映射。

|JSON类型|动态映射推断的映射类型|
|:----|:----|
|null|没有字段被添加，即不添加该映射|
|布尔型（true或者false）|boolean类型|
|浮点类型数字|单精度浮点型（float类型）|
|数字|长整型（long类型）|
|内嵌对象|object类型|
|数组|由数组的第一个非null值决定|
|字符串类型|根据字符串内容特征而定，有可能为text/keyword/double/long/date类型等|

### 字段类型

Elasticsearch的字段类型主要分为：核心类型、复合类型、地理类型、特殊类型

#### 核心类型

|类型 	|具体类型|
|:----|:----|
|字符串类型 	|text、keyword|
|数字类型 	|long、integer、short、byte、double、float、half_float、scaled_float|
|日期类型 	|date、date_nanos|
|布尔类型 	|boolean|
|二进制类型 	|binary|
|范围类型 	|integer_range、float_range、long_range、double_range、date_range|

字符串类型:

Elasticsearch 7.x有两种字符串类型：text和keyword，在Elasticsearch 5.x之后string类型已经不再支持。

- text: text类型适用于需要被全文检索的字段，例如新闻正文、邮件内容等比较长的文字。text类型会被Lucene分词器（Analyzer）处理为一个个词项，并使用Lucene倒排索引存储。text字段不能被用于排序。如果需要使用该类型的字段只需要在定义映射时指定JSON中对应字段的type为text。
- keyword: keyword适合简短、结构化字符串，例如主机名、姓名、商品名称等，可以用于过滤、排序、聚合检索，也可以用于精确查询。

数字类型:

|类型|说明|
|:----|:----|
|byte|-128 至 127|
|short|-32768 至 32767|
|integer|-2 ^ 31 至 2 ^ 31 - 1|
|long|-2 ^ 63 至 2 ^ 63 - 1|
|float|IEEE 754标准单精度浮点类型，4字节|
|double|IEEE 754标准双精度浮点类型，8字节|
|half_float|IEEE 754标准半精度浮点类型，2字节|
|scaled_float|缩放类型浮点类型|

    注： 数字类型的字段在满足需求的前提下应当尽量选择范围较小的数据类型，字段长度越短，搜索效率越高。
        对于浮点数，可以优先考虑使用scaled_float类型，该类型可以通过缩放因子来精确浮点数，12.34可以转换为1234来存储。

日期类型:

在Elasticsearch中日期可以为以下形式：

- 格式化的日期字符串，例如2019-01-01 00:00、2019/01/01
- 时间戳（和1970-01-01 00:00:00 UTC的差值），单位毫秒或者秒
    
    
    注： 即使是格式化的日期字符串，Elasticsearch底层依然采用的是时间戳的形式存储。
    
布尔类型:

JSON文档中同样存在布尔类型，不过JSON字符串类型也可以被Elasticsearch转换为布尔类型存储，前提是字符串的取值为"true"或者"false"。布尔类型常用于检索中的过滤条件。

二进制类型:

二进制类型binary接受BASE64编码的字符串，默认store属性为false，并且不可以被搜索。

范围类型:

范围类型可以用来表达一个数据的区间，主要有integer_range、float_range、long_range、double_range、date_range五种类型。
    
    date_range: 也可以表达64位时间戳（单位毫秒）范围。

#### 复合类型

Elasticsearch的复合类型主要有array、object、nested，分别用来表示数组类型、对象类型和嵌套类型。

数组类型:

因为Lucene底层并不真正支持数组类型，所以自然Elasticsearch也没有专用的数组类型。对于文档中的数组而言，每个元素必须是同一种类型。例如：[1,2,3]、["a","b","c"]、[{"name":"Trump"},{"name":"Smith"}]是合法的。

之前提到动态映射会以数组的第一个元素的类型来决定整个数组的类型，如果是空数组，那么会当成null来处理，即忽略该数组字段，不会创建该字段的映射。

对象类型:

JSON字符串允许嵌套对象，一个文档可以嵌套多个、多层对象。可以通过object类型来存储二级文档，不过由于Lucene并没有内部对象的概念，Elasticsearch会将原JSON文档扁平化。

嵌套类型:

nested类型可以看成是一个特殊的object类型，可以让对象数组独立检索。

#### 地理类型

Elasticsearch的地理类型字段分为两种：经纬度类型和地理区域类型。

经纬度类型:

经纬度类型字段(geo_point)可以存储经纬度相关信息。通过地理类型的字段，可以用来实现诸如查找在指定地理区域内相关的文档、根据距离排序、根据地理位置修改评分规则等需求。如果需要使用此类型，需要手动定义映射。

地理区域类型:

经纬度类型可以表达一个点，而地理区域类型(geo_shape)可以表达一块地理区域，区域的形状可以是任意多边形，也可以是点、线、面、多点、多线、多面等几何类型。

#### 特殊类型

IP类型(ip): IP类型的字段可以用来存储IPv4或者IPv6地址，如果需要存储IP类型的字段，需要手动定义映射。
join类型(join): join类型是Elasticsearch 6.x引入的类型，以取代淘汰的_parent元字段。用来实现文档的一对一、一对多的关系。

### 元字段

元字段是映射中描述文档本身的字段，所有的元字段类型都以下划线开头，元字段可以分为：

- 文档属性的元字段

|字段名|作用|
|:----|:----|
|_index|文档所属的索引|
|_type|文档类型，在ES 7以上版本中类型已被废弃，所以该值一律为_doc|
|_id|文档唯一ID|


- 源文档的元字段

|字段名|作用|
|:----|:----|
|_source|文档原始JSON字符串|
|_size|_source字段的大小|

- 索引的元字段

|字段名|作用|
|:----|:----|
|_all|包含索引全部字段的超级字段|
|_field_names|文档中包含非空值的所有字段|

- 路由的元字段

|字段名|作用|
|:----|:----|
|_routing|将文档路由到特定分片的自定义路由值|

- 自定义元字段: _meta，用于自定义元数据。




### 常用接口

#### 创建索引

1. 简单创建

请求:

    put book

响应:
    
    {
      "acknowledged" : true,
      "shards_acknowledged" : true,
      "index" : "book"
    }
    
2. 添加参数

请求:

    put book
    {
      "settings": {
        "number_of_shards": 3,
        "number_of_replicas": 2
      } 
    }
    
响应:
    
    {
      "acknowledged" : true,
      "shards_acknowledged" : true,
      "index" : "book"
    }
    
3. 指定mappings

请求:

    put book
    {
      "settings": {
        "number_of_shards": 3,
        "number_of_replicas": 2
      },
      "mappings": {
        "properties": {
          "name": {
            "type": "text",
            "fields": {
               "keyword": {
                "type": "keyword", 
                "ignore_above": 256
              }
            }
          },
          "isbn": {
            "type": "text",
            "fields": {
               "keyword": {
                "type": "keyword", 
                "ignore_above": 256
              }
            }
          },
          "count": {
            "type": "integer"
          }
        }
      }
    }
    
响应:

    {
      "acknowledged" : true,
      "shards_acknowledged" : true,
      "index" : "book"
    }
    
#### 查看索引

1. 查看指定索引配置

请求:

    get book/_settings

响应:
    
    {
      "book" : {
        "settings" : {
          "index" : {
            "creation_date" : "1581406276648",
            "number_of_shards" : "3",
            "number_of_replicas" : "2",
            "uuid" : "EU15QmYgSVGBqotZXBd6bQ",
            "version" : {
              "created" : "7050299"
            },
            "provided_name" : "book"
          }
        }
      }
    }

2. 查看多个索引配置


请求:

    get book,student/_settings

响应:
    
    {
      "student" : {
        "settings" : {
          "index" : {
            "creation_date" : "1581400441252",
            "number_of_shards" : "1",
            "number_of_replicas" : "1",
            "uuid" : "1YF_VH8-Sna76MAbnCNYbQ",
            "version" : {
              "created" : "7050299"
            },
            "provided_name" : "student"
          }
        }
      },
      "book" : {
        "settings" : {
          "index" : {
            "creation_date" : "1581406276648",
            "number_of_shards" : "3",
            "number_of_replicas" : "2",
            "uuid" : "EU15QmYgSVGBqotZXBd6bQ",
            "version" : {
              "created" : "7050299"
            },
            "provided_name" : "book"
          }
        }
      }
    }
    
#### 删除索引

请求:

    delete book

响应:
    
    {
      "acknowledged" : true
    }
    
#### 打开与关闭索引

1. 打开索引

请求:

    post book/_open

响应:
    
    {
      "acknowledged" : true,
      "shards_acknowledged" : true
    }
    
2. 关闭索引

请求:

    post book/_close

响应:
    
    {
      "acknowledged" : true,
      "shards_acknowledged" : true,
      "indices" : {
        "book" : {
          "closed" : true
        }
      }
    }
    
#### 新增文档

1. 指定文档id

请求:

    put book/_doc/1
    {
      "name": "Sring Cloud微服务实战",
      "isbn": "9787121313011",
      "count": 10
    }

响应:

    {
      "_index" : "book",
      "_type" : "_doc",
      "_id" : "1",
      "_version" : 1,
      "result" : "created",
      "_shards" : {
        "total" : 3,
        "successful" : 1,
        "failed" : 0
      },
      "_seq_no" : 0,
      "_primary_term" : 7
    }
    
2. 不指定文档id

请求:
    
    注：不指定文档id，只能使用post请求，不可以使用put请求
    
    post book/_doc
    {
      "name": "Sring Cloud微服务实战",
      "isbn": "9787121313011",
      "count": 10
    }

响应:
    
    {
      "_index" : "book",
      "_type" : "_doc",
      "_id" : "dTtAM3AB45U7kfEw7dy4",
      "_version" : 1,
      "result" : "created",
      "_shards" : {
        "total" : 3,
        "successful" : 1,
        "failed" : 0
      },
      "_seq_no" : 2,
      "_primary_term" : 7
    }
    
3. 当新增文档时有不存在的字段，则动态更新索引的映射

例如：

    post book/_doc/1
    {
      "name": "Sring Cloud微服务实战",
      "isbn": "9787121313011",
      "count": 10,
      "price": 89.00
    }

请求参数说明：
    
    index/type/id
    
响应参数说明：

- _index：文档所在的索引名
- _type：文档所在的类型名
- _id：文档ID
- _version：文档的版本
- result：created已经创建
- _shards： _shards表示索引操作的复制过程的信息。
    - total：指示应在其上执行索引操作的分片副本（主分片和副本分片）的数量。
    - successful：表示索引操作成功的分片副本数。
    - failed：在副本分片上索引操作失败的情况下包含复制相关错误。
- _seq_no:
- _primary_term: 

#### 查看文档

请求:

    get book/_doc/1

响应:
    
    id为1的文档存在:
    
    {
      "_index" : "book",
      "_type" : "_doc",
      "_id" : "1",
      "_version" : 1,
      "_seq_no" : 0,
      "_primary_term" : 7,
      "found" : true,
      "_source" : {
        "name" : "Sring Cloud微服务实战",
        "isbn" : "9787121313011",
        "count" : 10
      }
    }
    
    id为1的文档不存在:
    
    {
      "_index" : "book",
      "_type" : "_doc",
      "_id" : "1",
      "found" : false
    }

响应参数说明：
- found： 存在结果时为true，不存在则为false
    
#### 删除文档

请求:
    
    delete book/_doc/1

响应:
    
    {
      "_index" : "book",
      "_type" : "_doc",
      "_id" : "1",
      "_version" : 2,
      "result" : "deleted",
      "_shards" : {
        "total" : 3,
        "successful" : 1,
        "failed" : 0
      },
      "_seq_no" : 4,
      "_primary_term" : 7
    }
    
#### 简单检索

1. term查询

请求:

    get book/_search
    {
      "query": {
        "term": {
          "name": {
            "value": "高性能"
          }
        }
      }
    }
    
响应:

    {
      "took" : 1,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1,
          "relation" : "eq"
        },
        "max_score" : 0.2876821,
        "hits" : [
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "1",
            "_score" : 0.2876821,
            "_source" : {
              "name" : "高性能MYSQL",
              "isbn" : "9787121198854",
              "count" : 10,
              "price" : 128.0
            }
          }
        ]
      }
    }

2. 分页查询

请求:
    
    get book/_search
    {
      "from": 0,
      "size": 3,
      "query": {
        "match_all": {}
      }
    }
    
响应:
    
    {
      "took" : 305,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 3,
          "relation" : "eq"
        },
        "max_score" : 1.0,
        "hits" : [
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "3",
            "_score" : 1.0,
            "_source" : {
              "name" : "Spring Cloud微服务实战",
              "isbn" : "9787121313011",
              "count" : 10,
              "price" : 12.0
            }
          },
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "2",
            "_score" : 1.0,
            "_source" : {
              "name" : "Netty实战",
              "isbn" : "9787115453686",
              "count" : 10,
              "price" : 69
            }
          },
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "1",
            "_score" : 1.0,
            "_source" : {
              "name" : "高性能MYSQL",
              "isbn" : "9787121198854",
              "count" : 10,
              "price" : 128.0
            }
          }
        ]
      }
    }

3. 显示指定字段

请求:
    
    get book/_search
    {
      "_source": ["name", "isbn"],
      "query": {
        "term": {
          "price": 128.00
        }
      }
    }
    
响应:
    
    {
      "took" : 1,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1,
          "relation" : "eq"
        },
        "max_score" : 1.0,
        "hits" : [
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "1",
            "_score" : 1.0,
            "_source" : {
              "isbn" : "9787121198854",
              "name" : "高性能MYSQL"
            }
          }
        ]
      }
    }

4. 显示version

请求:
    
    get book/_search
    {
      "version": true,
      "query": {
        "term": {
          "price": 128.00
        }
      }
    }
    
响应:

    {
      "took" : 1,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1,
          "relation" : "eq"
        },
        "max_score" : 1.0,
        "hits" : [
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "1",
            "_version" : 1,
            "_score" : 1.0,
            "_source" : {
              "name" : "高性能MYSQL",
              "isbn" : "9787121198854",
              "count" : 10,
              "price" : 128.0
            }
          }
        ]
      }
    }

5. 评分过滤

请求:
    
    get book/_search
    {
      "min_score": 0.2,
      "query": {
        "term": {
          "name": "高性能"
        }
      }
    }

响应:
    
    {
      "took" : 0,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1,
          "relation" : "eq"
        },
        "max_score" : 0.2876821,
        "hits" : [
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "1",
            "_score" : 0.2876821,
            "_source" : {
              "name" : "高性能MYSQL",
              "isbn" : "9787121198854",
              "count" : 10,
              "price" : 128.0
            }
          }
        ]
      }
    }

6. 高亮关键字

请求:
    
    get book/_search
    {
      "query": {
        "match": {
          "name": {
            "query": "高性能"
          }
        }
      },
      "highlight": {
        "pre_tags": [
          "<strong>"
        ],
        "post_tags": [
          "</strong>"
        ],
        "fields": {
          "name": {}
        }
      }
    }
    
响应:

    {
      "took" : 1,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1,
          "relation" : "eq"
        },
        "max_score" : 0.5753642,
        "hits" : [
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "1",
            "_score" : 0.5753642,
            "_source" : {
              "name" : "高性能MYSQL",
              "isbn" : "9787121198854",
              "count" : 10,
              "price" : 128.0
            },
            "highlight" : {
              "name" : [
                "<strong>高性能</strong>MYSQL"
              ]
            }
          }
        ]
      }
    }

7. terms查询

返回包含任何一个给定值的数据。

请求:
    
    get book/_search
    {
      "query": {
        "terms": {
          "name": [
            "高性能",
            "实战"
          ]
        }
      }
    }

响应:
    
    {
      "took" : 1,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 3,
          "relation" : "eq"
        },
        "max_score" : 1.0,
        "hits" : [
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "3",
            "_score" : 1.0,
            "_source" : {
              "name" : "Spring Cloud微服务实战",
              "isbn" : "9787121313011",
              "count" : 10,
              "price" : 12.0
            }
          },
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "2",
            "_score" : 1.0,
            "_source" : {
              "name" : "Netty实战",
              "isbn" : "9787115453686",
              "count" : 10,
              "price" : 69
            }
          },
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "1",
            "_score" : 1.0,
            "_source" : {
              "name" : "高性能MYSQL",
              "isbn" : "9787121198854",
              "count" : 10,
              "price" : 128.0
            }
          }
        ]
      }
    }

8. 简单match查询

与term查询的区别：match在匹配时会对所查找的关键词进行分词，然后按分词匹配查找，而term会直接对关键词进行查找。

请求:

    get book/_search
    {
      "query": {
        "match": {
          "name": "服务实战"
        }
      }
    }
    
响应:

    {
      "took" : 1,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 2,
          "relation" : "eq"
        },
        "max_score" : 1.3022472,
        "hits" : [
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "3",
            "_score" : 1.3022472,
            "_source" : {
              "name" : "Spring Cloud微服务实战",
              "isbn" : "9787121313011",
              "count" : 10,
              "price" : 12.0
            }
          },
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "2",
            "_score" : 0.22920427,
            "_source" : {
              "name" : "Netty实战",
              "isbn" : "9787115453686",
              "count" : 10,
              "price" : 69
            }
          }
        ]
      }
    }

9. 使用操作符的match查询

请求:
    
    // 使用and操作符，对查询条件进行分词后查找
    get book/_search
    {
     "query": {
       "match": {
         "name": {
           "query": "服务实战",
           "operator": "and"
         }
       }
     }
    }
    
响应:

    {
      "took" : 3,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1,
          "relation" : "eq"
        },
        "max_score" : 1.3022472,
        "hits" : [
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "3",
            "_score" : 1.3022472,
            "_source" : {
              "name" : "Spring Cloud微服务实战",
              "isbn" : "9787121313011",
              "count" : 10,
              "price" : 12.0
            }
          }
        ]
      }
    }


10. match_phrase查询（短语查询）

使用match_phrase查询，会对查询内容进行分词，返回的数据需要满足下列要求：
- 分词后的所有词项需要出现在该字段中
- 字段中的词项顺序要一致

请求:

    get book/_search
    {
      "query": {
        "match_phrase": {
          "name": "服务实战"
        }
      }
    }
    
响应:

    {
      "took" : 1,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1,
          "relation" : "eq"
        },
        "max_score" : 1.3022472,
        "hits" : [
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "3",
            "_score" : 1.3022472,
            "_source" : {
              "name" : "Spring Cloud微服务实战",
              "isbn" : "9787121313011",
              "count" : 10,
              "price" : 12.0
            }
          }
        ]
      }
    }

11. match_phrase_prefix查询（短语前缀查询）

match_phrase_prefix与match_phrase基本相同，只是它允许查询条件进行分词后的最后一个词满足前缀匹配即可。

请求:
    
    get book/_search
    {
      "query": {
        "match_phrase_prefix": {
          "name": "Spring Clou"
        }
      }
    }
    
响应:

    {
      "took" : 1,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1,
          "relation" : "eq"
        },
        "max_score" : 1.1508858,
        "hits" : [
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "3",
            "_score" : 1.1508858,
            "_source" : {
              "name" : "Spring Cloud微服务实战",
              "isbn" : "9787121313011",
              "count" : 10,
              "price" : 12.0
            }
          }
        ]
      }
    }

12. multi_match查询（多字段查询）

请求:
    
    get book/_search
    {
      "query": {
         "multi_match": {
           "query": "9787115453686 Cloud",
           "fields": ["name", "isbn"]
         }
      }
    }
    
响应:

    {
      "took" : 1,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 2,
          "relation" : "eq"
        },
        "max_score" : 0.6931472,
        "hits" : [
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "2",
            "_score" : 0.6931472,
            "_source" : {
              "name" : "Netty实战",
              "isbn" : "9787115453686",
              "count" : 10,
              "price" : 69
            }
          },
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "3",
            "_score" : 0.5754429,
            "_source" : {
              "name" : "Spring Cloud微服务实战",
              "isbn" : "9787121313011",
              "count" : 10,
              "price" : 12.0
            }
          }
        ]
      }
    }
    
13. common_terms查询（常用词查询）

请求:
    
    
响应:  

14. range查询（范围查询）

请求:

    get book/_search
    {
      "query": {
        "range": {
          "price": {
            "gte": 100,
            "lte": 200
          }
        }
      }
    }
    
响应:

    {
      "took" : 3,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1,
          "relation" : "eq"
        },
        "max_score" : 1.0,
        "hits" : [
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "1",
            "_score" : 1.0,
            "_source" : {
              "name" : "高性能MYSQL",
              "isbn" : "9787121198854",
              "count" : 10,
              "price" : 128.0
            }
          }
        ]
      }
    }

14. exists查询

可匹配的记录：
- 字段存在且值非空
- 字段存在但为空字符串“”
- 字段类型为数组且至少有一个值非空

请求:
    
    get book/_search
    {
      "query": {
        "exists": {
          "field": "author"
        }
      }
    }
    
响应: 

    {
      "took" : 964,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 2,
          "relation" : "eq"
        },
        "max_score" : 1.0,
        "hits" : [
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "5",
            "_score" : 1.0,
            "_source" : {
              "name" : "C++入门",
              "isbn" : "231316464227",
              "count" : 10,
              "price" : 123.0,
              "author" : "张三"
            }
          },
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "2",
            "_score" : 1.0,
            "_source" : {
              "name" : "Netty实战",
              "isbn" : "9787115453686",
              "count" : 10,
              "price" : 69,
              "author" : ""
            }
          }
        ]
      }
    }

15. prefix查询（前缀查询）

请求:
    
    get book/_search
    {
      "query": {
        "prefix": {
          "name": "spring"
        }
      }
    }
    
响应: 

    {
      "took" : 6,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1,
          "relation" : "eq"
        },
        "max_score" : 1.0,
        "hits" : [
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "3",
            "_score" : 1.0,
            "_source" : {
              "name" : "Spring Cloud微服务实战",
              "isbn" : "9787121313011",
              "count" : 10,
              "price" : 12.0
            }
          }
        ]
      }
    }

16. wildcard查询（通配符查询）

请求:
    
    get book/_search
    {
      "query": {
        "wildcard": {
          "name": "*实战*"
        }
      }
    }
    
响应: 
    
    {
      "took" : 4,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 2,
          "relation" : "eq"
        },
        "max_score" : 1.0,
        "hits" : [
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "3",
            "_score" : 1.0,
            "_source" : {
              "name" : "Spring Cloud微服务实战",
              "isbn" : "9787121313011",
              "count" : 10,
              "price" : 12.0
            }
          },
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "2",
            "_score" : 1.0,
            "_source" : {
              "name" : "Netty实战",
              "isbn" : "9787115453686",
              "count" : 10,
              "price" : 69,
              "author" : ""
            }
          }
        ]
      }
    }

17. regexp查询（正则表达式查询）

请求:
    
    get book/_search
    {
      "query": {
        "regexp": {
          "name": ".*实战.*"
        }
      }
    }
    
响应: 

    {
      "took" : 2,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 2,
          "relation" : "eq"
        },
        "max_score" : 1.0,
        "hits" : [
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "3",
            "_score" : 1.0,
            "_source" : {
              "name" : "Spring Cloud微服务实战",
              "isbn" : "9787121313011",
              "count" : 10,
              "price" : 12.0
            }
          },
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "2",
            "_score" : 1.0,
            "_source" : {
              "name" : "Netty实战",
              "isbn" : "9787115453686",
              "count" : 10,
              "price" : 69,
              "author" : ""
            }
          }
        ]
      }
    }

18. fuzzy查询（模糊查询）

请求:
    
    get book/_search
    {
      "query": {
        "fuzzy": {
          "name": "clood"
        }
      }
    }
    
响应: 

    {
      "took" : 1,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1,
          "relation" : "eq"
        },
        "max_score" : 0.46035436,
        "hits" : [
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "3",
            "_score" : 0.46035436,
            "_source" : {
              "name" : "Spring Cloud微服务实战",
              "isbn" : "9787121313011",
              "count" : 10,
              "price" : 12.0
            }
          }
        ]
      }
    }


19. type查询

请求:
    
    
响应: 

20. ids查询

请求:

    get /_search 
    {
      "query": {
        "ids": {
          "values": [1, 2]
        }
      }
    }
    
响应: 

    {
      "took" : 2,
      "timed_out" : false,
      "_shards" : {
        "total" : 18,
        "successful" : 18,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 3,
          "relation" : "eq"
        },
        "max_score" : 1.0,
        "hits" : [
          {
            "_index" : "book1",
            "_type" : "_doc",
            "_id" : "2",
            "_score" : 1.0,
            "_source" : {
              "name" : "Netty实战",
              "isbn" : "9787115453686",
              "count" : 10,
              "price" : 69,
              "author" : ""
            }
          },
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "2",
            "_score" : 1.0,
            "_source" : {
              "name" : "Netty实战",
              "isbn" : "9787115453686",
              "count" : 10,
              "price" : 69,
              "author" : ""
            }
          },
          {
            "_index" : "book",
            "_type" : "_doc",
            "_id" : "1",
            "_score" : 1.0,
            "_source" : {
              "name" : "高性能MYSQL",
              "isbn" : "9787121198854",
              "count" : 10,
              "price" : 128.0
            }
          }
        ]
      }
    }

#### 复合查询

1. Bool查询

在单独的查询中组合任意数量的查询：

- must: 必须匹配，查询的文档必须满足条件。

- should: 应该匹配，不影响查询结果，如果查询条件满足则会影响评分，不满足则没有任何影响。

- must_not: 不能匹配，查询的文档必须不满足条件。

- filter: 必须匹配，这种语句不影响评分。

请求：

    get douban_book_index/_search
    {
      "query": {
        "bool": {
          "must": [
            {
              "match": {
                "name": "java并发"
              }
            },
            {
              "range": {
                "price": {
                  "gte": 90,
                  "lte": 100
                }
              }
            }
          ],
          "must_not": [
            {
              "match": {
                "name": "实践"
              }
            }
          ],
          "should": [
            {
              "match": {
                "publisher": "电子工业"
              }
            }
          ],
          "filter": {
            "bool": {
              "must": [
                {
                  "match": {
                    "name": "高级"
                  }
                }
              ]
            }
          }
        }
      }
    }
    
响应： 

    {
      "took" : 8,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1,
          "relation" : "eq"
        },
        "max_score" : 4.355178,
        "hits" : [
          {
            "_index" : "douban_book_index",
            "_type" : "_doc",
            "_id" : "981",
            "_score" : 4.355178,
            "_source" : {
              "_class" : "com.holmes.springboot.ealsticsearch.entity.Book",
              "id" : 981,
              "name" : "Java Web高级编程",
              "price" : 99.8,
              "author" : "威廉斯 (Nicholas S.Williams) / 清华大学出版社",
              "publisher" : "清华大学出版社",
              "pubDate" : "2015-6-1",
              "point" : 8.5,
              "commentCount" : "82",
              "summary" : "Java成为世界上编程语言之一是有其优势的。熟悉JavaSE的程序员可以轻松地进入到Java EE开发中，构建出安全、可靠和具有扩展性的企业级应用程序。编写...",
              "img" : "https://img3.doubanio.com/view/subject/s/public/s28314031.jpg"
            }
          }
        ]
      }
    }

2. constant_score

查询以非评分模式进行，可以省去评分过程，提高查询效率

请求：

    get douban_book_index/_search
    {
      "query": {
        "constant_score": {
          "filter": {
            "bool": {
              "must": [
                {
                  "match": {
                    "name": "java并发"
                  }
                },
                {
                  "range": {
                    "price": {
                      "gte": 90,
                      "lte": 100
                    }
                  }
                }
              ],
              "must_not": [
                {
                  "match": {
                    "name": "实践"
                  }
                }
              ],
              "should": [
                {
                  "match": {
                    "publisher": "电子工业"
                  }
                }
              ],
              "filter": {
                "bool": {
                  "must": [
                    {
                      "match": {
                        "name": "高级"
                      }
                    }
                  ]
                }
              }
            }
          }
        }
      }
    }


响应：

    {
      "took" : 0,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1,
          "relation" : "eq"
        },
        "max_score" : 1.0,
        "hits" : [
          {
            "_index" : "douban_book_index",
            "_type" : "_doc",
            "_id" : "981",
            "_score" : 1.0,
            "_source" : {
              "_class" : "com.holmes.springboot.ealsticsearch.entity.Book",
              "id" : 981,
              "name" : "Java Web高级编程",
              "price" : 99.8,
              "author" : "威廉斯 (Nicholas S.Williams) / 清华大学出版社",
              "publisher" : "清华大学出版社",
              "pubDate" : "2015-6-1",
              "point" : 8.5,
              "commentCount" : "82",
              "summary" : "Java成为世界上编程语言之一是有其优势的。熟悉JavaSE的程序员可以轻松地进入到Java EE开发中，构建出安全、可靠和具有扩展性的企业级应用程序。编写...",
              "img" : "https://img3.doubanio.com/view/subject/s/public/s28314031.jpg"
            }
          }
        ]
      }
    }


#### 指标聚合

1. max

请求：获取最高的豆瓣评分
    
    get douban_book_index/_search
    {
      "size": 0,
      "aggs": {
        "max_point": {
          "max": {
            "field": "point"
          }
        }
      }
    }

响应：

    {
      "took" : 0,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1000,
          "relation" : "eq"
        },
        "max_score" : null,
        "hits" : [ ]
      },
      "aggregations" : {
        "max_point" : {
          "value" : 9.899999618530273
        }
      }
    }

2. min

请求：获取最低的豆瓣评分
    
    get douban_book_index/_search
    {
      "size": 0,
      "aggs": {
        "min_point": {
          "min": {
            "field": "point"
          }
        }
      }
    }

响应：

    {
      "took" : 2,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1000,
          "relation" : "eq"
        },
        "max_score" : null,
        "hits" : [ ]
      },
      "aggregations" : {
        "min_point" : {
          "value" : 6.199999809265137
        }
      }
    }


3. avg

请求：获取价格平均值

    get douban_book_index/_search
    {
      "size": 0,
      "aggs": {
        "avg_price": {
          "avg": {
            "field": "price"
          }
        }
      }
    }

响应：
    
    {
      "took" : 4,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1000,
          "relation" : "eq"
        },
        "max_score" : null,
        "hits" : [ ]
      },
      "aggregations" : {
        "avg_price" : {
          "value" : 83.82747675097839
        }
      }
    }

4. sum

请求：获取所有价格的和

    get douban_book_index/_search
    {
      "size": 0,
      "aggs": {
        "sum_price": {
          "sum": {
            "field": "price"
          }
        }
      }
    }

响应：

    {
      "took" : 2,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1000,
          "relation" : "eq"
        },
        "max_score" : null,
        "hits" : [ ]
      },
      "aggregations" : {
        "sum_price" : {
          "value" : 81061.1700181961
        }
      }
    }
    
5. cardinality

基数统计，求某字段不同的值的个数。

请求：

    get douban_book_index/_search
    {
      "size": 0,
      "aggs": {
        "cardinality_point": {
          "cardinality": {
            "field": "point"
          }
        }
      }
    }

响应：

    {
      "took" : 0,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1000,
          "relation" : "eq"
        },
        "max_score" : null,
        "hits" : [ ]
      },
      "aggregations" : {
        "cardinality_point" : {
          "value" : 34
        }
      }
    }
    
6. value_count

统计包含某一字段的文档数

请求：

    get douban_book_index/_search
    {
      "size": 0,
      "aggs": {
        "count_point": {
          "value_count": {
            "field": "point"
          }
        }
      }
    }
    
响应：

    {
      "took" : 0,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1000,
          "relation" : "eq"
        },
        "max_score" : null,
        "hits" : [ ]
      },
      "aggregations" : {
        "count_point" : {
          "value" : 380
        }
      }
    }


7. stats

请求：一次获取价格的各种指标

    get douban_book_index/_search
    {
      "size": 0,
      "aggs": {
        "stats_price": {
          "stats": {
            "field": "price"
          }
        }
      }
    }

响应：
    
    {
      "took" : 0,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1000,
          "relation" : "eq"
        },
        "max_score" : null,
        "hits" : [ ]
      },
      "aggregations" : {
        "stats_price" : {
          "count" : 967,
          "min" : 2.25,
          "max" : 2019.0,
          "avg" : 83.82747675097839,
          "sum" : 81061.1700181961
        }
      }
    }

8. extended_stats

扩展统计，比stats显示更多的聚合指标

请求：

    get douban_book_index/_search
    {
      "size": 0,
      "aggs": {
        "extended_stats_price": {
          "extended_stats": {
            "field": "price"
          }
        }
      }
    }

响应：

    {
      "took" : 0,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1000,
          "relation" : "eq"
        },
        "max_score" : null,
        "hits" : [ ]
      },
      "aggregations" : {
        "extended_stats_price" : {
          "count" : 967,
          "min" : 2.25,
          "max" : 2019.0,
          "avg" : 83.82747675097839,
          "sum" : 81061.1700181961,
          "sum_of_squares" : 2.698948146905095E7,
          "variance" : 20883.483065091532,
          "std_deviation" : 144.51118664342746,
          "std_deviation_bounds" : {
            "upper" : 372.84985003783333,
            "lower" : -205.19489653587652
          }
        }
      }
    }

9. percentiles、percentile_ranks

[说明](https://www.elastic.co/guide/cn/elasticsearch/guide/current/percentiles.html)

请求：

    get douban_book_index/_search
    {
      "size": 0,
      "aggs": {
        "percentiles_point": {
          "percentiles": {
            "field": "point"
            , "percents": [
              1,
              5,
              25,
              50,
              75,
              95,
              99
            ]
          }
        }
      }
    }

响应：

    {
      "took" : 1,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1000,
          "relation" : "eq"
        },
        "max_score" : null,
        "hits" : [ ]
      },
      "aggregations" : {
        "percentiles_point" : {
          "values" : {
            "1.0" : 6.860000133514404,
            "5.0" : 7.450000047683716,
            "25.0" : 8.300000190734863,
            "50.0" : 8.800000190734863,
            "75.0" : 9.199999809265137,
            "95.0" : 9.5,
            "99.0" : 9.770000076293945
          }
        }
      }
    }


#### 桶聚合

1. 分组

请求：按豆瓣评分进行分组，并求每个分组的的书籍的平均价格

    get douban_book_index/_search
    {
      "size": 0,
      "aggs": {
        "point_count": {
          "terms": {
            "field": "point"
          },
          "aggs": {
            "avg_price": {
              "avg": {
                "field": "price"
              }
            }
          }
        }
      }
    }

响应：

    {
      "took" : 0,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1000,
          "relation" : "eq"
        },
        "max_score" : null,
        "hits" : [ ]
      },
      "aggregations" : {
        "point_count" : {
          "doc_count_error_upper_bound" : 3,
          "sum_other_doc_count" : 145,
          "buckets" : [
            {
              "key" : 9.300000190734863,
              "doc_count" : 35,
              "avg_price" : {
                "value" : 78.7623529995189
              }
            },
            {
              "key" : 9.0,
              "doc_count" : 32,
              "avg_price" : {
                "value" : 129.89343750476837
              }
            },
            {
              "key" : 9.100000381469727,
              "doc_count" : 28,
              "avg_price" : {
                "value" : 76.53678563662938
              }
            },
            {
              "key" : 8.399999618530273,
              "doc_count" : 22,
              "avg_price" : {
                "value" : 76.89090902155095
              }
            },
            {
              "key" : 8.699999809265137,
              "doc_count" : 22,
              "avg_price" : {
                "value" : 72.5
              }
            },
            {
              "key" : 8.899999618530273,
              "doc_count" : 20,
              "avg_price" : {
                "value" : 65.58900012969971
              }
            },
            {
              "key" : 8.199999809265137,
              "doc_count" : 19,
              "avg_price" : {
                "value" : 63.73684210526316
              }
            },
            {
              "key" : 8.800000190734863,
              "doc_count" : 19,
              "avg_price" : {
                "value" : 63.55263157894737
              }
            },
            {
              "key" : 9.199999809265137,
              "doc_count" : 19,
              "avg_price" : {
                "value" : 80.66555574205186
              }
            },
            {
              "key" : 9.399999618530273,
              "doc_count" : 19,
              "avg_price" : {
                "value" : 92.15631585372121
              }
            }
          ]
        }
      }
    }

注：如果需要对text类型的字段进行桶聚合，需要设置字段 fielddata=true，fielddata默认为false，因为开启Text的fielddata对内存的占用很高。
修改方式如下：

    PUT douban_book_index/_mapping
    {
      "properties": {
        "publisher": { 
          "type": "text",
          "analyzer" : "ik_max_word",
          "fielddata": true
        }
      }
    }

请求：对出版社名进行分组，经过观察查询响应信息可知，分组是根据分词的结果来进行的，因此分词器的选择会影响统计结果。

    get douban_book_index/_search
    {
      "size": 0,
      "aggs": {
        "publisher_count": {
          "terms": {
            "field": "publisher"
          }
        }
      }
    }

响应：

    {
      "took" : 0,
      "timed_out" : false,
      "_shards" : {
        "total" : 3,
        "successful" : 3,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 1000,
          "relation" : "eq"
        },
        "max_score" : null,
        "hits" : [ ]
      },
      "aggregations" : {
        "publisher_count" : {
          "doc_count_error_upper_bound" : 16,
          "sum_other_doc_count" : 1349,
          "buckets" : [
            {
              "key" : "出版",
              "doc_count" : 800
            },
            {
              "key" : "社",
              "doc_count" : 786
            },
            {
              "key" : "出版社",
              "doc_count" : 785
            },
            {
              "key" : "工业",
              "doc_count" : 329
            },
            {
              "key" : "人民",
              "doc_count" : 303
            },
            {
              "key" : "人民邮电",
              "doc_count" : 294
            },
            {
              "key" : "邮电",
              "doc_count" : 294
            },
            {
              "key" : "机械",
              "doc_count" : 176
            },
            {
              "key" : "机械工业",
              "doc_count" : 176
            },
            {
              "key" : "电子",
              "doc_count" : 155
            }
          ]
        }
      }
    }

2. 




 


参考: 

[官方中文文档](https://www.elastic.co/guide/cn/elasticsearch/guide/current/index.html)

[博客专题](https://blog.csdn.net/chengyuqiang/category_9271005.html)

[mappings介绍](https://blog.csdn.net/abc123lzf/article/details/102957060)

[索引管理](https://cloud.tencent.com/developer/article/1436463)

