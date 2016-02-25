package by.htp.internetshop.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.internetshop.command.CommandName;
import by.htp.internetshop.command.ICommand;
import by.htp.internetshop.command.impl.AddNewCategoryProductCommand;
import by.htp.internetshop.command.impl.AddNewPoductCommand;
import by.htp.internetshop.command.impl.AddToBlacklistCommand;
import by.htp.internetshop.command.impl.AuthorizationCommand;
import by.htp.internetshop.command.impl.CancelOrderCommand;
import by.htp.internetshop.command.impl.EditProductCommand;
import by.htp.internetshop.command.impl.GoAddProductCommand;
import by.htp.internetshop.command.impl.GoEditProductCommand;
import by.htp.internetshop.command.impl.LanguageCommand;
import by.htp.internetshop.command.impl.LogOutCommand;
import by.htp.internetshop.command.impl.NoSuchCommand;
import by.htp.internetshop.command.impl.OrderCommand;
import by.htp.internetshop.command.impl.RegistrationCommand;
import by.htp.internetshop.command.impl.RemoveFromBlacklistCommand;
import by.htp.internetshop.command.impl.RemoveProductCommand;
import by.htp.internetshop.command.impl.ShowBlacklistCommand;
import by.htp.internetshop.command.impl.ShowCorrectClientsCommand;
import by.htp.internetshop.command.impl.ShowInformationAboutProductCommand;
import by.htp.internetshop.command.impl.ShowOrdersOfOneClientCommand;

public final class ControllerHelper {

	private static final ControllerHelper instance = new ControllerHelper();
	private Map<CommandName, ICommand> commands = new HashMap<>();

	public ControllerHelper() {
		System.out.println("Начало инициализации controllerHelper");
		commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.ADD_NEW_CATEGORY_PRODUCT, new AddNewCategoryProductCommand());
		commands.put(CommandName.ADD_NEW_PRODUCT, new AddNewPoductCommand());
		commands.put(CommandName.ORDER, new OrderCommand());
		commands.put(CommandName.ADD_TO_BLACKLIST, new AddToBlacklistCommand());
		commands.put(CommandName.REMOVE_FROM_BLACKLIST, new RemoveFromBlacklistCommand());
		commands.put(CommandName.EDIT_PRODUCT, new EditProductCommand());
		commands.put(CommandName.REMOVE_PRODUCT, new RemoveProductCommand());
		commands.put(CommandName.CANCEL_ORDER, new CancelOrderCommand());
		commands.put(CommandName.LOG_OUT, new LogOutCommand());
		commands.put(CommandName.CHOOSE_LANGUAGE, new LanguageCommand());
		commands.put(CommandName.SHOW_CORRECT_CLIENTS, new ShowCorrectClientsCommand());
		commands.put(CommandName.SHOW_BLACKLIST, new ShowBlacklistCommand());
		commands.put(CommandName.SHOW_INFORMATION_ABOUT_PRODUCT, new ShowInformationAboutProductCommand());
		commands.put(CommandName.GO_ADD_PRODUCT, new GoAddProductCommand());
		commands.put(CommandName.GO_EDIT_PRODUCT, new GoEditProductCommand());
		commands.put(CommandName.SHOW_ORDERS, new ShowOrdersOfOneClientCommand());
		commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
		System.out.println("Инициализировали controllerHelper");
	}

	public static ControllerHelper getInstance() {
		return instance;
	}

	public ICommand getCommand(String commandName) {
		System.out.println("до расчета name");
		CommandName name = CommandName.valueOf(commandName.toUpperCase());
		System.out.println("name="+name);
		ICommand command;
		if (name != null) {
			command = commands.get(name);
		} else {
			command = commands.get(CommandName.NO_SUCH_COMMAND);
		}
		return command;
	}
}
