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
@XmlRootElement(name = "cartas")
public class CartaXML {

    private File file;
    private List<Carta> cartas;

    public CartaXML() {
        file = new File("src\\br\\uefs\\ecomp\\jogodamesada\\includes\\cartas.xml");
    }    

    /**
     * @return
     */
    @XmlElement(name = "carta")
    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public List LendoXML() {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(CartaXML.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            CartaXML wrapper = (CartaXML) um.unmarshal(file);
            List cartasXML = wrapper.getCartas();
            return cartasXML;
        } catch (JAXBException ex) {            
            System.out.println(ex.getMessage());
            return null;
        }        
    }

    public void escrevendoXML(List cartas) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(CartaXML.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            CartaXML wrapper = new CartaXML();
            wrapper.setCartas(cartas);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
