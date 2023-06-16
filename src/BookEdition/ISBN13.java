package BookEdition;

import Utilities.ObjectPlus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * COMPLEX ATTRIBUTE
 */

public class ISBN13 extends ObjectPlus implements Serializable {
    //I assumed that books will use the ISBN 13 because it is a standard since 2007
    private static final Map<Integer, String> codesAndCountriesMap = new HashMap<>();
    private static final File codesAndCountriesFile = new File("country_code.csv");
    private static final int prefix = 978;
    private int countryCode, checkSum;
    private String publisherCode, publicationNumber; //Not int because of the possible leading zeros

    public ISBN13(int countryCode, String publisherCode, String publicationNumber) throws Exception {
        super();
        setCountryCode(countryCode);
        setPublisherCode(publisherCode);
        setPublicationNumber(publicationNumber);
        setCheckSum();
        if (this.toString().replace("-", "").length() != 13)
            throw new Exception("ISBN has wrong length!");
    }

    public int getCountryCode() {
        return countryCode;
    }

    /**
     * Check the map in order to validate if the specified number is assigned to a country.
     */
    public void setCountryCode(int countryCode) {
        if (!codesAndCountriesMap.containsKey(countryCode))
            throw new IllegalArgumentException("Illegal country code!");
        this.countryCode = countryCode;
    }

    public String getPublisherCode() {
        return publisherCode;
    }

    /**
     * Publisher is assigned a single identification code. Its length may vary, but usually it is between 2 and 7 characters.
     * Because it is possible for the code to contain leading zeros,
     * I decided to treat it as a string and match it with regex [0-9]+ that checks if the string contains only numbers.
     */
    public void setPublisherCode(String publisherCode) {
        if (publisherCode == null || publisherCode.isBlank())
            throw new IllegalArgumentException("Empty code!");

        publisherCode = publisherCode.trim();

        if (publisherCode.length() < 2 || publisherCode.length() > 7)
            throw new IllegalArgumentException("Wrong length!");
        if (!publisherCode.matches("[0-9]+"))
            throw new IllegalArgumentException("Code can contain only letters!");
        this.publisherCode = publisherCode;
    }

    public String getPublicationNumber() {
        return publicationNumber;
    }

    /**
     * Publications numbers always have values in range 0-9, 00-99, 000-999; 0000-9999, 00000-99999
     * Because it is possible for the code to contain leading zeros,
     * I decided to treat it as a string and match it with regex [0-9]+ that checks if the string contains only numbers.
     */
    public void setPublicationNumber(String publicationNumber) {
        if (publicationNumber == null || publicationNumber.isBlank())
            throw new IllegalArgumentException("Empty publication number!");

        publicationNumber = publicationNumber.trim();

        if (publicationNumber.length() < 1 || publicationNumber.length() > 5)
            throw new IllegalArgumentException("Wrong publication number length!");
        if (!publicationNumber.matches("[0-9]+"))
            throw new IllegalArgumentException("Publication number can contain only letters!");
        this.publicationNumber = publicationNumber;
    }

    public int getCheckSum() {
        return checkSum;
    }

    /**
     * Checksum formula: 10 - (n1 + n2 * 3 +  n3 + n4 * 3 + n5 + n6 * 3 + n7 + n8 * 3 + n9 + n10 * 3 + n11 + n12 * 3) % 10
     */
    private void setCheckSum() {
        String ISBNWithoutChecksum = String.valueOf(prefix).concat(String.valueOf(this.countryCode)).concat(this.publisherCode).concat(this.publicationNumber);
        int checksum = 0;
        for (int i = 0; i < ISBNWithoutChecksum.length(); i++) {
            int weight = (i % 2 == 0 ? 1 : 3); //calculate the weight
            checksum += Character.getNumericValue(ISBNWithoutChecksum.charAt(i)) * weight;
        }
        checksum = 10 - checksum % 10;
        this.checkSum = checksum;
    }

    public static void writeCodesAndCountriesIntoMap() {
        try (BufferedReader br = new BufferedReader(new FileReader(codesAndCountriesFile))) {
            Integer code;
            String country, line;
            while ((line = br.readLine()) != null) {
                String[] codeAndCountry = line.split(",");
                code = Integer.parseInt(codeAndCountry[0].trim());
                country = codeAndCountry[1].trim();
                codesAndCountriesMap.put(code, country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getPrefix() {
        return prefix;
    }

    public static Map<Integer, String> getCodesAndCountriesMap() {
        return Collections.unmodifiableMap(codesAndCountriesMap);
    }

    @Override
    public String toString() {
        return prefix + "-" + countryCode + "-" + publisherCode + "-" + publicationNumber + "-" + checkSum;
    }
}
