package org.crsh.grails;

import java.lang.Object;
import java.util.Collections;

public class GrailsBootstrap extends org.crsh.spring.SpringWebBootstrap {

  /** . */
  private org.codehaus.groovy.grails.commons.GrailsApplication app;

  public org.codehaus.groovy.grails.commons.GrailsApplication getGrailsApplication() {
    return app;
  }

  public void setGrailsApplication(org.codehaus.groovy.grails.commons.GrailsApplication app) {
    this.app = app;
  }

  protected org.crsh.plugin.PluginContext start(
      java.util.Map<String, Object> attributes,
      org.crsh.plugin.PluginDiscovery discovery,
      ClassLoader loader) {
    attributes = new java.util.HashMap<String, Object>(attributes);
    if (app != null) {
      attributes.put("application", app);
    }
    return super.start(Collections.unmodifiableMap(attributes), discovery, loader);
  }
}