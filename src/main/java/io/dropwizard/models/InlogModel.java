package io.dropwizard.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InlogModel {

    @XmlElement
    public String param1;
    @XmlElement
    public String param2;
}
