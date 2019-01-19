package ua.cjhrxS.DTO;

public class SignUpDTO {
	
	
	private String first_name;
	private String last_name;
	private String user_name;
	private String pass_word;

	
	public SignUpDTO(String first_name, String last_name, String user_name, String pass_word) {
		
		
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_name = user_name;
		this.pass_word = pass_word;

	}


	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @return the pass_word
	 */
	public String getPass_word() {
		return pass_word;
	}

	/**
	 * @param pass_word the pass_word to set
	 */
	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SignUpDTO [first_name=" + first_name + ", last_name=" + last_name + ", user_name=" + user_name
				+ ", pass_word=" + pass_word + "]";
	}

	

}
