/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.justinschaaf.cafe.scriptengine;

import javax.tools.SimpleJavaFileObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * I honestly do not remember what the fuck this class was for.
 */
public class DynamicJavaFileObject extends SimpleJavaFileObject {

    Kind kind;

    public DynamicJavaFileObject(File file) {
        super(file.toURI(), Kind.OTHER);
        this.kind = getKindFromFile(file);
    }

    public static Kind getKindFromFile(File file) {

        String extension = file.getName().substring(file.getName().lastIndexOf("."));

        try {
            return Kind.valueOf(extension);
        } catch (Exception e) {
            return Kind.OTHER;
        }

    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(openInputStream()));
        StringBuilder content = new StringBuilder();

        reader.lines().forEachOrdered((line) -> content.append(line).append("\n"));

        return content.toString();

    }

    @Override
    public Kind getKind() {
        return this.kind;
    }

}