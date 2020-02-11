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

- 自定义元字段: meta，用于自定义元数据。

### 


### 常用接口

#### 创建索引








参考博客: 

[https://blog.csdn.net/abc123lzf/article/details/102957060]

[https://cloud.tencent.com/developer/article/1436463]

[https://blog.csdn.net/chengyuqiang/category_9271005.html]