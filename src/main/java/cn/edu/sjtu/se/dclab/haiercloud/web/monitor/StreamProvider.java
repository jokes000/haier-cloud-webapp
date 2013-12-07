package cn.edu.sjtu.se.dclab.haiercloud.web.monitor;

import java.io.IOException;
import java.io.InputStream;

/**
 * A provider of input stream from a property source.
 */
public interface StreamProvider {
	public InputStream readFrom(String spec) throws IOException;
}
