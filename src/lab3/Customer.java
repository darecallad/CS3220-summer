package lab3;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Customer {

	static int count = 0;
	static final int NUMBER_OF_ASSIGNMENTS = 5;
	
	int id;
	String firstName, lastName;
	String email;
	String password;
	String IdString;
	String HashedId;
	
	public Customer(String firstName, String lastName, String email, String password) throws NoSuchAlgorithmException{
		this.id = count++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		IdString = Integer.toString(id);
		HashedId =  HashingTheString(IdString);
		}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}
	public String getHashedId() {
		return HashedId;
	}

	public static String HashingTheString(String message) throws NoSuchAlgorithmException{
		
		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(message.getBytes(StandardCharsets.UTF_8));
		
		    StringBuffer hexString = new StringBuffer();
		    for (int i = 0; i < hash.length; i++) 
		    {
		    String hex = Integer.toHexString(0xff & hash[i]);
		    if(hex.length() == 1) hexString.append('0');
		        hexString.append(hex);
		    }
		    return hexString.toString();
		
	}		
}
