/*
 * Copyright 2002-2019 Igor Maznitsa (http://www.igormaznitsa.com)
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.igormaznitsa.jcp.expression.functions.xml;

import com.igormaznitsa.jcp.context.PreprocessorContext;
import com.igormaznitsa.jcp.expression.Value;
import com.igormaznitsa.jcp.expression.ValueType;
import com.igormaznitsa.meta.annotation.MustNotContainNull;

import javax.annotation.Nonnull;

/**
 * The class implements the xml_elementsnumber function
 *
 * @author Igor Maznitsa (igor.maznitsa@igormaznitsa.com)
 */
public final class FunctionXML_SIZE extends AbstractXMLFunction {

  private static final ValueType[][] ARG_TYPES = new ValueType[][] {{ValueType.STRING}};

  @Override
  @Nonnull
  public String getName() {
    return "xml_size";
  }

  @Nonnull
  public Value executeStr(@Nonnull final PreprocessorContext context, @Nonnull final Value elementListId) {
    return Value.valueOf(Long.valueOf(getElementListSize(context, elementListId.asString())));
  }

  @Override
  public int getArity() {
    return 1;
  }

  @Override
  @Nonnull
  @MustNotContainNull
  public ValueType[][] getAllowedArgumentTypes() {
    return ARG_TYPES;
  }

  @Override
  @Nonnull
  public String getReference() {
    return "element list size";
  }

  @Override
  @Nonnull
  public ValueType getResultType() {
    return ValueType.INT;
  }

}
