/*
 * Copyright (C) 2012 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

import org.crsh.cmdline.annotations.Argument
import org.crsh.cmdline.annotations.Command
import org.crsh.cmdline.annotations.Usage
import org.crsh.shell.impl.command.CRaSHSession
import org.crsh.command.GroovyScriptCommand
import org.crsh.command.CRaSHCommand

class evaluate extends CRaSHCommand {

    @Command
    @Usage("evaluate groovy script")
    void main(@Usage("the code") @Argument String scriptText) {
        CRaSHSession session = context.session
        session['grailsApplication'] = context.attributes.beans['grailsApplication']
        GroovyShell shell = session.getGroovyShell()
        GroovyScriptCommand script = shell.parse(scriptText)
        script.setSession(session)
        script.open(context)
        script.flush()
        script.close()
    }
}
