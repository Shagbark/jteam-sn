package ru.jteam.social.network.exception;

/**
 * @author protsko on 03.07.18
 */
public class RecordExistsException extends RuntimeException {

    public RecordExistsException(String tableName, Object object) {
        super("Record in table \"" + tableName + "\" with such key \"" + String.valueOf(object) + "\" exists.");
    }

}
