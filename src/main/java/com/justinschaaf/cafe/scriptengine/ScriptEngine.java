/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.justinschaaf.cafe.scriptengine;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Optional;

/**
 * This was a test to compile individual Java files at runtime, then load and
 * execute them from the main program. A working example is located in the test
 * directory, contained in the package of the same name.
 * <p>
 * Written around May 2020.
 *
 * @author justinhschaaf
 */
public class ScriptEngine {

    public ScriptEngine() {}

    /**
     * Loads a script from the given path and returns an instance of it. The
     * script should have a no-args constructor to create it.
     *
     * @param scriptFile The script file to compile and load.
     * @return An optional containing an Object instance of the loaded script,
     *    or {@link Optional#empty()} if {@link #compileScript(Path)} failed.
     * @param <T> The class/interface the script should extend
     * @throws Exception If there are any hiccups loading the script.
     */
    public <T> Optional<T> loadScript(Path scriptFile) throws Exception {

        boolean compiled = compileScript(scriptFile);

        if (compiled) {

            URL target = scriptFile.getParent().toUri().toURL();

            URLClassLoader scriptLoader = new URLClassLoader(new URL[]{target});

            String name = scriptFile.getFileName().toString();
            Class<?> scriptClass = scriptLoader.loadClass(name.substring(0, name.lastIndexOf(".")));

            Object obj = scriptClass.getDeclaredConstructor().newInstance();

            return Optional.of((T) obj);

        }

        return Optional.empty();

    }

    /**
     * Compiles the Java file at the given path, returning true if the
     * compilation was successful.
     *
     * @param scriptFile The path to the file to execute
     * @return true if the compilation task was successful
     * @throws IOException If initializing the {@link StandardJavaFileManager}
     *     fails
     */
    public boolean compileScript(Path scriptFile) throws IOException {

        // Get the system compiler instance
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        // Use try-with-resources on the file manager to automatically close it
        try (StandardJavaFileManager manager = compiler.getStandardFileManager(null, Locale.ENGLISH, Charset.defaultCharset())) {

            // This probably compiles and loads it
            JavaCompiler.CompilationTask task = compiler.getTask(
                            null,
                            manager,
                            null,
                            null,
                            null,
                            manager.getJavaFileObjects(scriptFile));

            return task.call();

        }

    }

}
