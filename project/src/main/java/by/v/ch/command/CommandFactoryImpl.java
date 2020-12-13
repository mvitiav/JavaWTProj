package by.v.ch.command;

import by.v.ch.command.commands.*;
import by.v.ch.exceptions.CommandNotFoundException;
import by.v.ch.services.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class CommandFactoryImpl implements CommandFactory{
    static Logger logger = LoggerFactory.getLogger(CommandFactory.class);
    private static CommandFactory instance;
    private HashMap<String,Command> commands;

    public CommandFactoryImpl() {
    commands=new HashMap<>();
    //todo:load all comands from comands folder
        commands.put("login_cmd",new LogInCommand());
        commands.put("about_cmd",new ShowInfoCommand());
        commands.put("order_cmd",new MakeOrderCommand());
        commands.put("home_cmd",new GoHomeCommand());
        commands.put("register_cmd",new RegisterCommand());
        commands.put("logout_cmd",new LogOutCommand());
        commands.put("distribute_cmd",new DistributeOrdersCommand());
    }

    public static CommandFactory getInstance() {
        if(instance==null){
            instance=new CommandFactoryImpl();
        }
        return instance;
    }

    @Override
    public Command getCommand(String commandName) throws CommandNotFoundException{
        System.out.println("commandName");
        if (commands.containsKey(commandName)){
            System.out.println("contains");
            return commands.get(commandName);
        }
        else
            {
                System.out.println("throwing");
                throw(new CommandNotFoundException(commandName));
            }
    }
}
