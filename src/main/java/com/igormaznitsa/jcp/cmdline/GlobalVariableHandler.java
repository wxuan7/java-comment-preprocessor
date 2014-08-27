/* 
 * Copyright 2014 Igor Maznitsa (http://www.igormaznitsa.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.igormaznitsa.jcp.cmdline;

import com.igormaznitsa.jcp.context.PreprocessorContext;
import com.igormaznitsa.jcp.expression.*;
import com.igormaznitsa.jcp.utils.PreprocessorUtils;
import java.util.Locale;

/**
 * The handler for global variables, it adds met global variables into the
 * inside storage
 *
 * @author Igor Maznitsa (igor.maznitsa@igormaznitsa.com)
 */
public class GlobalVariableHandler implements CommandLineHandler {

  private static final String ARG_NAME = "/P:";

  @Override
  public String getDescription() {
    return "set a global variable, for instance /P:DEBUG=true (use $ instead \" char)";
  }

  @Override
  public boolean processCommandLineKey(final String key, final PreprocessorContext context) {
    boolean result = false;

    if (key != null && !key.isEmpty() && key.toUpperCase(Locale.ENGLISH).startsWith(ARG_NAME)) {

      final String nameAndExpression = PreprocessorUtils.extractTrimmedTail(ARG_NAME, key);

      if (!nameAndExpression.isEmpty()) {

        final String[] splitted = PreprocessorUtils.splitForSetOperator(nameAndExpression);
        if (splitted.length != 2) {
          throw new IllegalArgumentException("Wrong expression at a " + ARG_NAME + " directive [" + nameAndExpression + ']');
        }

        final String value = splitted[0];
        final String expression = splitted[1];

        if (context.containsGlobalVariable(value)) {
          throw new IllegalArgumentException("Duplicated global definition detected [" + value + ']');
        }

        final Value resultVal = Expression.evalExpression(expression, context);
        context.setGlobalVariable(value, resultVal);
        result = true;
      }
    }
    return result;
  }

  @Override
  public String getKeyName() {
    return ARG_NAME;
  }
}