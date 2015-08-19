package org.workhorse.var;

import com.google.inject.TypeLiteral;

/**
 * Key for variable types.
 */
public class VariableKey {

   public static final TypeLiteral<VariableKey> TYPE_LITERAL = new TypeLiteral<VariableKey>() {};

   String className;
   Object qualifier;
    
   public static VariableKey get(Class<?> type) {
       return new VariableKey(type);
   }
    
   public static VariableKey get(Class<?> type, Object qualifier) {
       return new VariableKey(type, qualifier);
   }

   private VariableKey(Class<?> type) {
      this.className = type.getCanonicalName();
   }

   private VariableKey(Class<?> type, Object qualifier) {
      this(type);
      this.qualifier = qualifier;
   }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariableKey varKey = (VariableKey) o;
        return className.equals(varKey.className)
                && !(qualifier != null ? !qualifier.equals(varKey.qualifier) : varKey.qualifier != null);

    }

    @Override
    public int hashCode() {
        int result = className.hashCode();
        result = 31 * result + (qualifier != null ? qualifier.hashCode() : 0);
        return result;
    }
}
