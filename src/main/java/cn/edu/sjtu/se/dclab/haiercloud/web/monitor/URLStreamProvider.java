package cn.edu.sjtu.se.dclab.haiercloud.web.monitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * URL based implementation of a stream provider.
 */
public class URLStreamProvider implements StreamProvider {

	private final int connTimeout;
	private final int readTimeout;
	private final String path;
	private final String password;
	private final String type;
	private volatile SSLSocketFactory sslSocketFactory = null;

	/**
	 * Provide the connection timeout for the underlying connection.
	 * 
	 * @param connectionTimeout
	 *            time, in milliseconds, to attempt a connection, 1500
	 * @param readTimeout
	 *            the read timeout in milliseconds, 10000
	 */
	public URLStreamProvider(int connectionTimeout, int readTimeout,
			String path, String password, String type) {
		this.connTimeout = connectionTimeout; // 1500
		this.readTimeout = readTimeout; // 10000
		this.path = path; // ssl.trustStore.path
		this.password = password; // ssl.trustStore.password
		this.type = type; // ssl.trustStore.type
	}

	public InputStream readFrom(String spec) throws IOException {

		URLConnection connection = spec.startsWith("https") ? getSSLConnection(spec)
				: getConnection(spec);

		connection.setConnectTimeout(connTimeout);
		connection.setReadTimeout(readTimeout);
		connection.setDoOutput(true);

		return connection.getInputStream();
	}

	// ----- helper methods ----------------------------------------------------

	// Get a connection
	private URLConnection getConnection(String spec) throws IOException {
		return new URL(spec).openConnection();
	}

	// Get an ssl connection
	private HttpsURLConnection getSSLConnection(String spec) throws IOException {

		if (sslSocketFactory == null) {
			synchronized (this) {
				if (sslSocketFactory == null) {
					try {
						FileInputStream in = new FileInputStream(new File(path));
						KeyStore store = KeyStore
								.getInstance(type == null ? KeyStore
										.getDefaultType() : type);

						store.load(in, password.toCharArray());
						in.close();

						TrustManagerFactory tmf = TrustManagerFactory
								.getInstance(TrustManagerFactory
										.getDefaultAlgorithm());

						tmf.init(store);
						SSLContext context = SSLContext.getInstance("TLS");
						context.init(null, tmf.getTrustManagers(), null);

						sslSocketFactory = context.getSocketFactory();
					} catch (Exception e) {
						throw new IOException("Can't get connection.", e);
					}
				}
			}
		}
		HttpsURLConnection connection = (HttpsURLConnection) (new URL(spec)
				.openConnection());

		connection.setSSLSocketFactory(sslSocketFactory);

		return connection;
	}
}
