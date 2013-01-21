package chat;

public enum UserStatus {
	ONLINE,
	AWAY,
	DND,
	INVISIBLE,
	OFFLINE;
	
	public boolean isOnline(){
		return (!this.equals(UserStatus.OFFLINE));
	}
}
