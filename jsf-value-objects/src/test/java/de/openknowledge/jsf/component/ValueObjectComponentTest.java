/*
 * Copyright (C) Arne Limburg
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package de.openknowledge.jsf.component;

import de.openknowledge.jsf.validator.PhoneNumberValidator;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.warp.WarpTest;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.webapp30.WebAppDescriptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.io.File;

import javax.faces.webapp.FacesServlet;

@WarpTest
@RunAsClient
@RunWith(Arquillian.class)
public class ValueObjectComponentTest {

  @Drone
  private WebDriver browser;
  
  @Deployment
  public static WebArchive deployment() {
    return ShrinkWrap.create(WebArchive.class)
        .addClass(ValueObjectComponent.class)
        .addPackage(PhoneNumberValidator.class.getPackage())
        .addAsWebResource("test.xhtml")
        .addAsWebResource("META-INF/resources/ok/valueObject.xhtml", "resources/ok/valueObject.xhtml")
        .setWebXML(new StringAsset(Descriptors.create(WebAppDescriptor.class)
          .addDefaultNamespaces()
          .createServlet()
            .servletName("Faces Servlet")
            .servletClass(FacesServlet.class.getName())
          .up()
          .createServletMapping()
            .servletName("Faces Servlet")
            .urlPattern("*.xhtml")
          .up()
          .exportAsString()));
  }

  @Test
  public void page(@InitialPage TestPage page) {

  }

  @Test
  public void testComponent() {

  }
}