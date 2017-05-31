/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.model;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Gilvanei
 */
public class CasaXML {

    private File file;

    public CasaXML() {
        file = new File("src\\br\\uefs\\ecomp\\jogodamesada\\casas.xml");
    }
    private ArrayList<Casa> casas;

    /**
     * @return
     */
    @XmlElement(name = "casa")
    public ArrayList<Casa> getCartas() {
        return casas;
    }

    public void setCartas(ArrayList<Casa> casas) {
        this.casas = casas;
    }

    public ArrayList LendoXML() {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(CartaXML.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            CartaXML wrapper = (CartaXML) um.unmarshal(file);
            ArrayList casaXML = (ArrayList) wrapper.getCartas();
            return casaXML;
        } catch (JAXBException ex) {            
            System.out.println(ex.getMessage());
            return null;
        }        
    }

   public void escrevendoXML(ArrayList casas) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(CartaXML.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            CasaXML wrapper = new CasaXML();
            wrapper.setCartas(casas);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
