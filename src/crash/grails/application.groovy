public class application {

  @Command
  public void config(InvocationContext<Map> context) {
    def app = context.attributes.application
    app.flatConfig.each { k,v ->
      context.provide([name:k,value:v])
    }
  }

  @Command
  public void controllers(InvocationContext<Map> context) {
    def app = context.attributes.application
    app.controllerClasses.each { c ->
      context.provide([NAME:c.name,"DEFAULT ACTION":c.defaultAction,FULLNAME:c.fullName,URIS:c.URIs as List])
    }
  }

  @Command
  public void domains(InvocationContext<Map> context) {
    def app = context.attributes.application
    app.domainClasses.each { c ->
      context.provide([name:c.name])
    }
  }

  @Command
  public void services(InvocationContext<Map> context) {
    def app = context.attributes.application
    app.serviceClasses.each { c ->
      context.provide([NAME:c.name,DATASOURCE:c.datasource,TRANSACTIONAL:c.transactional,FULLNAME:c.fullName])
    }
  }

  @Command
  public void codecs(InvocationContext<Map> context) {
    def app = context.attributes.application
    app.codecClasses.each { c ->
      context.provide([name:c.name,FULLNAME:c.fullName])
    }
  }

  @Command
  public void taglibs(InvocationContext<Map> context) {
    def app = context.attributes.application
    app.tagLibClasses.each { c ->
      context.provide([name:c.name,NAMESPACE:c.namespace,"TAG NAMES":c.tagNames])
    }
  }

  @Command
  public void urlmappings(InvocationContext<Map> context) {
    def app = context.attributes.application
    app.urlMappingsClasses.each { c ->
      context.provide([name:c.name,"EXCLUDE PATTERNS":c.excludePatterns?:"",FULLNAME:c.fullName])
    }
  }

  @Command
  public void bootstraps(InvocationContext<Map> context) {
    def app = context.attributes.application
    app.bootstrapClasses.each { c ->
      context.provide([name:c.name,,FULLNAME:c.fullName])
    }
  }
}