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

    @Override
    public CommandFactory getInstance() {
        if(instance==null){
            instance=new CommandFactoryImpl();
        }
        return instance;
    }

    @Override
    public Command getCommand(String commandName) throws CommandNotFoundException{
        if (commands.containsKey(commandName)){
            return commands.get(commandName);
        }
        else
            {
                throw(new CommandNotFoundException(commandName));
            }
    }
}
