import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 
* @ClassName: HttpUtil
* @Description: ���������࣬���ڻ�ȡ��ҳԴ����
* @author jc
* @date 2018��7��24��
*
 */
public class HttpUtil {
	private static OkHttpClient okHttpClient;
	private static int num = 0;

	static {
		okHttpClient = new OkHttpClient.Builder().readTimeout(1, TimeUnit.SECONDS).connectTimeout(1, TimeUnit.SECONDS)
				.build();
	}

	public static String get(String path) {
		// �������ӿͻ���
		Request request = new Request.Builder().url(path).build();
		// �������ö���
		Call call = okHttpClient.newCall(request);
		try {
			Response response = call.execute();// ִ��
			if (response.isSuccessful()) {
				return response.body().string();
			}
		} catch (IOException e) {
			System.out.println("���Ӹ�ʽ����" + path);
		}
		return null;
	}
}
