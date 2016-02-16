package by.htp.internetshop.command;

public enum CommandName {
	AUTHORIZATION, REGISTRATION, ADD_NEW_PRODUCT, ADD_NEW_CATEGORY_PRODUCT, ADD_TO_BLACKLIST, REMOVE_FROM_BLACKLIST, ORDER, EDIT_PRODUCT, 
	REMOVE_PRODUCT, CANCEL_THE_ORDER, LOG_OUT, NO_SUCH_COMMAND, CHOOSE_LANGUAGE, SHOW_CORRECT_CLIENTS, SHOW_BLACKLIST, SHOW_INFORMATION_ABOUT_PRODUCT, 
	GO_ADD_PRODUCT, GO_EDIT_PRODUCT;

/*	String[] roles;

	private CommandName(String... role) {
		int i = 0;
		for (String s : role) {
			roles[i] = s;
			i++; ClientsThatAreNotIncludedInBlacklist
		}
	}

	private String[] getRoles() {
		return roles;
	}

	public static boolean isPermission(String commandName, String role) {
		boolean givePermission = false;

		CommandName name = CommandName.valueOf(commandName);
		String[] roles = name.getRoles();

		for (String _role : roles) {
			if (_role.equals(role.toLowerCase())) {
				givePermission = true;
				break;
			}
		}

		return givePermission;
	}*/
}
