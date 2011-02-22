/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.seam.international.test.datetimezone;

import java.util.List;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.international.datetimezone.AvailableDateTimeZones;
import org.jboss.seam.international.datetimezone.ForwardingDateTimeZone;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class AvailableDateTimeZonesTest {
    @Deployment
    public static JavaArchive createTestArchive() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(AvailableDateTimeZones.class, AvailableDateTimeZoneBean.class, ForwardingDateTimeZone.class)
                .addManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
    }

    @Inject
    Instance<AvailableDateTimeZoneBean> availBean;
    @Inject
    List<DateTimeZone> timeZones;

    @Test
    public void testAvailableTimeZonesProducerViaBean() {
        Assert.assertNotNull(availBean);
        List<DateTimeZone> list = availBean.get().getAvailTimeZones();
        Assert.assertNotNull(list);
        Assert.assertTrue(!list.isEmpty());
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testAvailableTimeZonesProducerDirect() {
        Assert.assertNotNull(timeZones);
        Assert.assertTrue(!timeZones.isEmpty());
        Assert.assertTrue(timeZones.size() > 0);
    }
}
