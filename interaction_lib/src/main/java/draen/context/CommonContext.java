package draen.context;

import draen.commands.CommandsStorage;
import draen.data.application.Progress;
import draen.input.IOManager;

public interface CommonContext<T extends CommonContext<T>> {
    CommandsStorage<T> getCommandsStorage();
    IOManager getIoManager();
    Progress getProgress();
    String[] getArgs();
}
