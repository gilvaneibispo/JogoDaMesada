/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.model;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gilvanei
 */
@XmlRootElement(name = "casas")
public class CasaXML {

    private final File file;
    private List<Casa> casas;

    public CasaXML() {
        //file = new File("src\\br\\uefs\\ecomp\\jogodamesada\\casas.xml");
          file = new File("src\\br\\uefs\\ecomp\\jogodamesada\\casas.xml");
    }
    
    /**
     * @return
     */
    @XmlElement(name = "casa")
    public List<Casa> getCasas() {
        return this.casas;
    }

    public void setCasas(List<Casa> casas) {
        this.casas = casas;
    }

    public List LendoXML() {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(CasaXML.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            CasaXML wrapper = (CasaXML) um.unmarshal(file);
            List casasXML =  wrapper.getCasas();
            return casasXML;
        } catch (JAXBException ex) {            
            System.out.println(ex.getMessage());
            return null;
        }        
    }

   public void escrevendoXML(List casas) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(CasaXML.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            CasaXML wrapper = new CasaXML();
            wrapper.setCasas(casas);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
