package ch.njol.skript.variables;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Expression;

/**
 * Stores variables of a scope. Usually, one scope stores all global variables,
 * and each trigger gets their own temporary local variable scope.
 */
public interface VariableScope {
	
	/**
	 * Gets a variable at given path.
	 * @param path Path to variable.
	 * @param event Currently executing event.
	 * @return A variable value, or null if given path does not contain
	 * a variable.
	 */
	@Nullable
	Object get(VariablePath path, @Nullable Event event);
	
	/**
	 * Sets a variable at given path. Parent list variables will be created
	 * as needed.
	 * @param path Path to variable.
	 * @param event Currently executing event.
	 * @param value A new value for the variable.
	 */
	void set(VariablePath path, @Nullable Event event, Object value);
	
	/**
	 * Appends a value to a list variable at given path. If there is no list
	 * variable there, a new one will be created.
	 * @param path Path to a list variable.
	 * @param event Currently executing event.
	 * @param value A value to append to the list.
	 */
	void append(VariablePath path, @Nullable Event event, Object value);
	
	/**
	 * Deletes a variable at given path. If there is no such variable, nothing
	 * will be done.
	 * @param path Path to variable to be deleted.
	 * @param event Currently executing event.
	 * @param deleteList If list variable deletion is desired.
	 */
	void delete(VariablePath path, @Nullable Event event, boolean deleteList);

	/**
	 * Merges contents of given list variable into variables in this scope.
	 * Unlike {@link #set(VariablePath, Event, Object)}, this will not remove
	 * lists under it, if there are any.
	 * 
	 * <p>If no list variable exists with given path at this scope, the given
	 * list will be set to it. Any previous single value there might've been
	 * will be set as a shadow value of that list.
	 * 
	 * <p>If a list already exists, contents of it and the given list will be
	 * merged. Should there be conflicts, the given list takes precedence.
	 * @param path Path to the list variable.
	 * @param event Currently executing event.
	 * @param list The list variable.
	 */
	void mergeList(VariablePath path, @Nullable Event event, ListVariable list);
}