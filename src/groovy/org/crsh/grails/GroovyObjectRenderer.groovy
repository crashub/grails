package org.crsh.grails;

import org.crsh.text.LineRenderer;
import org.crsh.text.Renderer;
import org.crsh.text.ui.RowElement;
import org.crsh.text.ui.TableElement;

/**
 * @author Julien Viet
 */
public class GroovyObjectRenderer extends Renderer<GroovyObject> {

  @Override
  public Class<GroovyObject> getType() {
    return GroovyObject.class;
  }

  @Override
  public LineRenderer renderer(Iterator<GroovyObject> stream) {

    ArrayList<GroovyObject> buffer = new ArrayList<GroovyObject>();
    LinkedHashSet<String> properties = new LinkedHashSet<String>();
    while (stream.hasNext()) {
      GroovyObject object = stream.next();
      buffer.add(object);
      MetaClass metaClass = object.getMetaClass();
      for (MetaProperty property : metaClass.getProperties()) {
        properties.add(property.getName());
      }
    }

    //
    TableElement table = new TableElement().leftCellPadding(1);
    RowElement header = new RowElement();
    for (String property : properties) {
      header.add(property);
    }
    for (GroovyObject object : buffer) {
      try {
        object.withNewSession { session ->
          session.update(object);
          table.add(getRow(object, properties));
        }
      }
      catch (MissingMethodException ignore) {
        table.add(getRow(object, properties));
      }
    }

    //
    return table.renderer();
  }

  private static RowElement getRow(GroovyObject object, Set<String> properties) {
    RowElement row = new RowElement();
    for (String property : properties) {
      Object value;
      try {
        value = object.getProperty(property);
      }
      catch (MissingPropertyException e) {
        value = null;
      }
      row.add(value != null ? value.toString() : "");
    }
    return row;
  }

}
