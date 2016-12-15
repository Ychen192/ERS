package dataTier;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Lookup and cache services. Reduces performances overhead of looking up services.
 * @author King
 */

public class ServiceLocator {

	private static DataSource ers;
	private static Properties env; // environment props

	static { // use to initialize static variables
			 // executed when class is loaded into JVM

		InputStream stream = ServiceLocator.class.getClassLoader().getResourceAsStream("resources/application.properties");
		env = new Properties();
		try {
			env.load(stream);
		} catch (IOException e) {
		}
	}

	public synchronized static DataSource getErsDatabase() throws Exception {
		if (ers == null) {
			ers = lookupErs();
		}
		return ers;
	}

	private static DataSource lookupErs() throws Exception {
		try {
			Context ctxt = new InitialContext(env);
			DataSource ds = (DataSource) ctxt.lookup(env.getProperty("ersdb"));
			ds.getConnection();
			return ds;
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
