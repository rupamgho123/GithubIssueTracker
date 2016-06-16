package com.twitter.githubissuetracker.providers;

import com.twitter.githubissuetracker.interfaces.Provider;
import com.twitter.githubissuetracker.services.GithubService;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by rupam.ghosh on 11/06/16.
 */
public class GithubServiceProvider implements Provider<GithubService> {
  @Override public GithubService get() {
    SSLContext sslContext = null;
    try {
      sslContext = SSLContext.getInstance("SSL");
      final TrustManager[] trustAllCerts = new TrustManager[] {
          new X509TrustManager() {
            @Override public X509Certificate[] getAcceptedIssuers() {
              X509Certificate[] cArrr = new X509Certificate[0];
              return cArrr;
            }

            @Override public void checkServerTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {
            }

            @Override public void checkClientTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {
            }
          }
      };
      sslContext.init(null, trustAllCerts, new SecureRandom());
    }catch (Exception ex){

    }
    OkHttpClient client = new OkHttpClient.Builder()
        .sslSocketFactory(sslContext.getSocketFactory())
        .hostnameVerifier(new HostnameVerifier() {
          @Override public boolean verify(String hostname, SSLSession session) {
            return true;
          }
        })
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .build();
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    return retrofit.create(GithubService.class);
  }
}
