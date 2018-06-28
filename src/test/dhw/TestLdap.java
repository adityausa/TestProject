package test.dhw;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.shiro.realm.ldap.LdapUtils;

public class TestLdap {

	private static final Pattern GROUP_REGEX = Pattern
			.compile("CN=ChildSupport ([A-Za-z\\-1-9\\s]+) \\- ([A-Za-z\\-1-9]+)");

	private static final List<String> VALID_GROUPS = Arrays.asList("System Staff");
	public static void main(String args[]) {
		Hashtable<String, String> env = new Hashtable<>();
		
		
		/*env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://dhwcodc:389");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.REFERRAL, "follow");
		env.put(Context.SECURITY_PRINCIPAL, "PAMAcct");
		env.put(Context.SECURITY_CREDENTIALS, "Yvm*Sc@r3Z2x#");*/
		

		  env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		  env.put(Context.PROVIDER_URL, "ldap://dhwcodc:389");
		  env.put(Context.SECURITY_AUTHENTICATION, "simple"); 
		  env.put(Context.REFERRAL,"follow");
		  env.put(Context.SECURITY_PRINCIPAL, "yeddanaa@dhw.state.gov");
		  env.put(Context.SECURITY_CREDENTIALS, "log2Nexi&");
		 

		DirContext context = null;
		try {
			context = new InitialDirContext(env);
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		SearchControls constraints = new SearchControls();
		constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);

		System.out.println("About to serach");
		try {
			NamingEnumeration<SearchResult> results =
					// context.search("cn=yeddanaa,dc=test", "(cn=yeddanaa)", constraints);

					context.search("DC=dhw,DC=state,DC=id,DC=us", "(sAMAccountName=yeddanaa)", constraints);

			if (results != null && results.hasMore()) {
				SearchResult sr = results.next();
				final Attributes attributes = sr.getAttributes();
				System.out.println(attributes.get("givenName").get().toString());
				System.out.println(attributes.get("sn").get().toString());
				System.out.println(attributes.get("memberOf").get(0).toString());
				System.out.println(attributes.get("info"));
				System.out.println(attributes.get("memberOf"));

				Enumeration vals = attributes.get("memberOf").getAll();

				while (vals.hasMoreElements()) {
					final List<String> roles = new ArrayList<>();
					String element = (String) vals.nextElement();
					StringTokenizer st = new StringTokenizer(element, ",");
					final String memberName = st.nextToken();
					Matcher matcher = GROUP_REGEX.matcher(memberName);
					if (matcher.matches() && matcher.groupCount() == 2) {
						System.out.println("Group 1: " + matcher.group(1) + " Group 2: " + matcher.group(2));
						if (matcher.group(2).equalsIgnoreCase("Dev")
								&& VALID_GROUPS.stream().anyMatch(vg -> vg.equalsIgnoreCase(matcher.group(1)))) {
							NamingEnumeration<SearchResult> result = context.search("DC=dhw,DC=state,DC=id,DC=us",
									"(" + memberName + ")", constraints);
							if (result != null && result.hasMore()) {
								SearchResult srs = result.next();
								String info = srs.getAttributes().get("info").get().toString();
								System.out.println("Result:" + info);
							}
						}
					}
				}

			}

			System.out.println("Serach done");

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
