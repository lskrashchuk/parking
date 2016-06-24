package by.lskrashchuk.training.parking.dataaccess.filters;


public class UserFilter extends AbstractFilter{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;

    private boolean isFetchCredentials;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isFetchCredentials() {
        return isFetchCredentials;
    }

    public void setFetchCredentials(boolean isFetchCredentials) {
        this.isFetchCredentials = isFetchCredentials;
    }

}
