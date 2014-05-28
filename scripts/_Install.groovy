//
// This script is executed by Grails after plugin was installed to project.
// This script is a Gant script so you can use all special variables provided
// by Gant (such as 'baseDir' which points on project base dir). You can
// use 'ant' to access a global instance of AntBuilder
//
// For example you can create directory under project tree:
//
//    ant.mkdir(dir:"${basedir}/grails-app/jobs")
//

import org.apache.commons.logging.LogFactory

// Get a logger
log = LogFactory.getLog("crash")

// Extracting crash commands in the user base dir
private void copyCmd(org.crsh.vfs.File src, File dst) throws IOException {
  log.info("Copying ${src.path.value} to ${dst}")
  if (src.hasChildren()) {
    if (!dst.exists()) {
      if (dst.mkdirs()) {
        log.info("Could not create dir " + dst.getCanonicalPath())
      }
    }
    if (dst.exists() && dst.isDirectory()) {
      for (org.crsh.vfs.File child : src.children()) {
        copyCmd(child, new File(dst, child.getName()))
      }
    } else {
      log.info("Cannot copy to non existing ${dst}")
    }
  } else {
    if (!dst.exists()) {
      def resource = src.getResource()
      if (resource != null) {
        log.info("Copied command " + src.getPath().getValue() + " to " + dst.getCanonicalPath())
        org.crsh.util.Utils.copy(new ByteArrayInputStream(resource.getContent()), new FileOutputStream(dst))
      }
    } else {
      log.info("Preserving existing ${dst}")
    }
  }
}

//
log.info("Starting CRaSH install")
try {
  def cmdFS = new org.crsh.vfs.FS()
  cmdFS.mount(Thread.currentThread().getContextClassLoader(), org.crsh.vfs.Path.get("/crash/commands/"))
  cmdFS.mount(new File("$crashPluginDir/src/crash/"));
  def src = cmdFS.get(org.crsh.vfs.Path.get("/"))
  def dst = new File("${basedir}/web-app/WEB-INF/crash/commands");
  copyCmd(src, dst)
} catch (Exception e) {
  // This should not prevent crash from running
  log.error("Could not copy CRaSH base scripts", e);
} finally {
  log.info("CRaSH installed")
}
