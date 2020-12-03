package by.v.ch.command;

import by.v.ch.exceptions.CommandNotFoundException;

import java.util.HashMap;

public class CommandFactoryImpl implements CommandFactory{
    private static CommandFactory instance;
    private HashMap<String,Command> commands;

    public CommandFactoryImpl() {
    commands=new HashMap<>();
    //todo:load all comands from comands folder
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
