package com.mircoservicetest.moexservice.parser;


import com.mircoservicetest.moexservice.exceptions.currencyXMLParsingException;
import com.mircoservicetest.moexservice.model.Currency;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class MOEXCurrencyXMLParser implements Parser{
    @Override
    public List<Currency> parse(String currenciesAsString) {
        ArrayList<Currency> currencies = new ArrayList<Currency>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();

            try (StringReader reader = new StringReader(currenciesAsString)) {
                Document doc = db.parse(new InputSource(reader));
                doc.getDocumentElement().normalize();

                NodeList dateList = doc.getElementsByTagName("data");
                NodeList list = null;

                for (int rowIdx = 0; rowIdx < dateList.getLength(); rowIdx++) {
                    Node node = dateList.item(rowIdx);
                    String id = node.getAttributes().getNamedItem("id").getNodeValue();
                    if (id.equals("wap_rates")) {
                        NodeList dataList2 = node.getChildNodes();
                        for (int dataIdx = 0; dataIdx < dataList2.getLength(); dataIdx++) {
                            Node node2 = dataList2.item(dataIdx);
                            String name = node2.getNodeName();
                            if (name.equals("rows")) {
                                list = node2.getChildNodes();
                            }
                        }
                    }
                }

                for (int rowIdx = 0; rowIdx < list.getLength(); rowIdx++) {
                    Node node = list.item(rowIdx);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        String tradedate =  element.getAttribute("tradedate");
                        String tradetime =  element.getAttribute("tradetime");
                        String secid =  element.getAttribute("secid");
                        String shortname =  element.getAttribute("shortname");
                        String price =  element.getAttribute("price");
                        String lasttoprevprice =  element.getAttribute("lasttoprevprice");
                        String nominal =  element.getAttribute("nominal");
                        String decimals =  element.getAttribute("decimals");

                        if(!tradedate.isEmpty() && !tradetime.isEmpty() && !secid.isEmpty()
                            && !shortname.isEmpty() && !price.isEmpty() && !lasttoprevprice.isEmpty()
                            && !lasttoprevprice.isEmpty() && !decimals.isEmpty()) {

                            currencies.add(new Currency(tradedate, tradetime, secid, shortname, price, lasttoprevprice, nominal, decimals));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            throw new currencyXMLParsingException(ex);
        }
        return currencies;
    }
}
