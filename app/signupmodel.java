public class signupmodel {

    private String username,email,password;
    private String USER_TYPE ;

    public String getUSER_TYPE() {
        return USER_TYPE;
    }

    public void setUSER_TYPE(String USER_TYPE) {
        this.USER_TYPE = USER_TYPE;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public signupmodel(String username, String email, String password,String userType) {
        this.username = username;
        this.email = email;
        this.password = password;
        USER_TYPE = userType;
    }
}