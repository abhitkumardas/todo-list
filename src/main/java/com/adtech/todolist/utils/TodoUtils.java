package com.adtech.todolist.utils;

import com.adtech.todolist.codetype.AppSource;
import com.adtech.todolist.config.TodoConstants;
import com.adtech.todolist.model.process.UserAgentInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
public class TodoUtils {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    public ObjectMapper mapper;

    @Autowired
    StringUtility stringUtility;

    public TodoUtils() {
        this.mapper = getMapperWithProperties(true, false, false, true);
    }

    public ObjectMapper getMapperWithProperties(Boolean initializeMapper, Boolean prototypeBean, Boolean enableIndentOutput, Boolean enableFailOnUnknownProperties) {

        ObjectMapper mapper = null;

        if (null == this.mapper || initializeMapper) {
            this.mapper = new ObjectMapper();
        }

        if (prototypeBean) {
            mapper = new ObjectMapper();
        } else {
            mapper = this.mapper;
        }

        if (null != enableFailOnUnknownProperties) {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, enableFailOnUnknownProperties);
        }

        if (null != enableIndentOutput) {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
        }

        return mapper;
    }

    public boolean isValidGUID(String guid) {
        return stringNotEmpty(guid) && guid.length() == 36;
    }

    public boolean isValidAppCode(String appCode) {
        return stringNotEmpty(appCode) && appCode.length() == 16;
    }

    public boolean isValidEmail(String email) {
        return stringNotEmpty(email) && email.matches(TodoConstants.EMAIL_REGEX);
    }

    public boolean isValidPassword(String password) {
        return stringNotEmpty(password) && password.length() >= 8;
    }

    public boolean stringNotEmpty(String text) {
        return null != text && !text.trim().isEmpty() && !text.trim().equals("\"\"");
    }

    public boolean stringEmpty(String text) {
        return null == text || text.trim().isEmpty() || text.trim().equals("\"\"");
    }

    public boolean isValidUrl(String url) {
        if (stringNotEmpty(url)) {
            try {
                new URL(url).toURI();
                return true;
            } catch (Exception e) {
                // add code to send an email to admin
                return false;
            }
        } else {
            return false;
        }
    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public Date stringToDate(String dateString, boolean isTimestamp) {
        DateTimeFormatter formatter;
        if (isTimestamp) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
            return localDateTimeToDate(dateTime);
        } else {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(dateString, formatter);
            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
    }

    public Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public LocalDate dateToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public String getFormattedTimeBetweenDate(Date startDate, Date endDate) {
        long millis = endDate.getTime() - startDate.getTime();
        return getFormattedTime(millis);
    }

    public String getFormattedTimeBetweenLocalDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Duration dur = Duration.between(startDateTime, endDateTime);
        long millis = dur.toMillis();
        return getFormattedTime(millis);
    }

    public String getFormattedTime(long milliseconds) {

        // Total seconds of difference (using Math.abs to avoid negative values)
        milliseconds = Math.abs(milliseconds);

        long seconds = 0, minutes = 0, hours = 0, days = 0;
        if (milliseconds >= 1000) {
            seconds = (milliseconds / 1000) % 60;
            minutes = ((milliseconds / (1000 * 60)) % 60);
            hours = ((milliseconds / (1000 * 60 * 60)) % 24);
            days = (milliseconds / (1000 * 60 * 60 * 24));
        }

        StringBuilder sb = new StringBuilder();
        if (days > 0) {
            sb.append(days + " days ");
        }
        if (hours > 0 && days <= 0) {
            sb.append(hours + " hrs ");
        }
        if (minutes > 0 && days <= 0 && hours <= 0) {
            sb.append(minutes + " min ");
        }
        if (seconds > 0 && days <= 0 && hours <= 0 && minutes <= 0) {
            sb.append(seconds + " sec");
        }

        return sb.toString();
    }

    public String getFormattedTimeWithMilliseconds(long milliseconds) {

        // Total seconds of difference (using Math.abs to avoid negative values)
        milliseconds = Math.abs(milliseconds);

        long seconds = 0, minutes = 0, hours = 0, days = 0;
        if (milliseconds >= 1000) {
            seconds = (milliseconds / 1000) % 60;
            minutes = ((milliseconds / (1000 * 60)) % 60);
            hours = ((milliseconds / (1000 * 60 * 60)) % 24);
            days = (milliseconds / (1000 * 60 * 60 * 24));
            milliseconds = milliseconds % 1000;
        }

        StringBuilder sb = new StringBuilder();
        if (days > 0) {
            sb.append(days + " days ");
        }
        if (hours > 0) {
            sb.append(hours + " hrs ");
        }
        if (minutes > 0) {
            sb.append(minutes + " min ");
        }
        if (seconds > 0) {
            sb.append(seconds + " sec ");
        }
        if (milliseconds > 0) {
            sb.append(milliseconds + " ms");
        }

        return sb.toString();
    }

    public String getFormattedStackTrace(Exception exception) {
        final StringBuilder result = new StringBuilder("StackTrace : ");
        final String NEW_LINE = "\n";
        result.append(NEW_LINE);
        result.append(NEW_LINE);
        result.append(exception.toString());
        result.append(NEW_LINE);

        //for each element of the stack trace
        for (StackTraceElement element : exception.getStackTrace()) {
            result.append(element);
            result.append(NEW_LINE);
        }
        return result.toString();
    }

    public String getUniqueHashId(String inputString) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] guidBytes = messageDigest.digest(inputString.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < guidBytes.length; i++) {
                stringBuilder.append(Integer.toString((guidBytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.fillInStackTrace() + " " + e.getMessage());
        }
        return null;
    }

    public AppSource getAppSourceBySource(String source) {
        if (null != source) {
            if (source.equalsIgnoreCase("DASHBOARD")) {
                return AppSource.DASHBOARD;
            } else if (source.equalsIgnoreCase("TWO_WAY_API")) {
                return AppSource.TWO_WAY_API;
            } else if (source.equalsIgnoreCase("ANDROID_APP")) {
                return AppSource.ANDROID_APP;
            } else if (source.equalsIgnoreCase("IOS_APP")) {
                return AppSource.IOS_APP;
            } else {
                return null;
            }
        }
        return null;
    }

    public String getNameBySlug(String slug) {
        String hyphen = "-";
        if (stringNotEmpty(slug) && slug.contains(hyphen)) {
            return slug.replace(hyphen, " ");
        }
        return slug;
    }

    public String getIPForRequest(HttpServletRequest servletRequest) {
        String requestedFromIP;
        if (null != servletRequest.getHeader("X-Real-IP")) {
            requestedFromIP = servletRequest.getHeader("X-Real-IP");
            logger.debug("Requested From IP, X-Real-IP " + requestedFromIP + " For API URL " + servletRequest.getRequestURL());
        } else if (null != servletRequest.getHeader("X-Forwarded-For")) {
            requestedFromIP = servletRequest.getHeader("X-Forwarded-For");
            logger.debug("Requested From IP, X-Forwarded-For " + requestedFromIP + " For API URL " + servletRequest.getRequestURL());
        } else {
            requestedFromIP = servletRequest.getRemoteAddr();
            logger.debug("Requested From IP, Remote Address " + requestedFromIP + " For API URL " + servletRequest.getRequestURL());
        }
        return requestedFromIP;
    }

    public UserAgentInfo getUserAgentInfoByUserAgentHeader(String userAgentHeader) {

        if (stringNotEmpty(userAgentHeader)) {

            String userAgent = userAgentHeader.toLowerCase();
            String os, browser;

            // =================OS=======================
            if (userAgent.toLowerCase().indexOf("windows") >= 0) {
                os = "Windows";
            } else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
                os = "Mac";
            } else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
                os = "Unix";
            } else if (userAgent.toLowerCase().indexOf("android") >= 0) {
                os = "Android";
            } else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
                os = "IPhone";
            } else {
                os = "UnKnown, More-Info: " + userAgentHeader;
            }

            // ===============Browser===========================
            try {

                if (userAgent.contains("msie")) {
                    String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
                    browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
                } else if (userAgent.contains("safari") && userAgent.contains("version")) {
                    browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-"
                            + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
                } else if (userAgent.contains("opr") || userAgent.contains("opera")) {
                    if (userAgent.contains("opera"))
                        browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-"
                                + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
                    else if (userAgent.contains("opr"))
                        browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
                                .replace("OPR", "Opera");
                    else
                        browser = "UnKnown, More-Info: " + userAgentHeader;
                } else if (userAgent.contains("chrome")) {
                    browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
                } else if ((userAgent.indexOf("mozilla/7.0") > -1) || (userAgent.indexOf("netscape6") != -1)
                        || (userAgent.indexOf("mozilla/4.7") != -1) || (userAgent.indexOf("mozilla/4.78") != -1)
                        || (userAgent.indexOf("mozilla/4.08") != -1) || (userAgent.indexOf("mozilla/3") != -1)) {
                    browser = "Netscape-?";

                } else if (userAgent.contains("firefox")) {
                    browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
                } else if (userAgent.contains("rv")) {
                    browser = "IE-" + userAgent.substring(userAgent.indexOf("rv") + 3, userAgent.indexOf(")"));
                } else {
                    browser = "UnKnown, More-Info: " + userAgentHeader;
                }

            } catch (StringIndexOutOfBoundsException e) {
                browser = "UnKnown, More-Info: " + userAgentHeader;
            }
            UserAgentInfo userAgentInfo = new UserAgentInfo();
            userAgentInfo.setBrowser(browser);
            userAgentInfo.setOperatingSystem(os);
            if (userAgent.indexOf("Mobile") != -1) {
                userAgentInfo.setDevice("MObile");
            } else {
                userAgentInfo.setDevice("Desktop");
            }
            return userAgentInfo;
        } else {
            // TODO: add code to send an email to admin
            logger.error("Unable to fetch User-Agent details while populating operating system and browser details");
            UserAgentInfo userAgentInfo = new UserAgentInfo();
            userAgentInfo.setBrowser(null);
            userAgentInfo.setOperatingSystem(null);
            return userAgentInfo;
        }
    }

    public PageRequest getPageRequest(int pageIndex, int pageSize) {

        pageSize = (pageSize >= 1 && pageSize <= 100) ? pageSize : 20;
        pageIndex = (pageIndex > 0) ? pageIndex : 0;

        return PageRequest.of(pageIndex, pageSize);
    }
    public PageRequest getPageRequestWithSortPropertyAndOrder(int pageIndex, int pageSize, Sort.Direction sortOrder, String sortProperty) {

        pageSize = (pageSize >= 1 && pageSize <= 100) ? pageSize : 20;
        pageIndex = (pageIndex > 0) ? pageIndex : 0;

        if (stringEmpty(sortProperty)) {
            sortProperty = "createdDate";
        }

        Sort sort = new Sort(sortOrder, sortProperty);
        return PageRequest.of(pageIndex, pageSize, sort);
    }

    public String encodeImageToBase64(String imagePath) {
        try {
            File file = new File(imagePath);
            byte[] imageData = Files.readAllBytes(file.toPath());
            return java.util.Base64.getEncoder().encodeToString(imageData);
        } catch (IOException e) {
            logger.error("Error while reading image for base54 conversion, ", e);
            return null;
        }
    }

    private String getBase64FileExtension(String base64) {
        byte[] base64Bytes = java.util.Base64.getMimeDecoder().decode(base64);
        Tika tika = new Tika();
        String fileType = tika.detect(base64Bytes);
        return getMimeTypeMap().get(fileType);
    }

    private Map<String, String> getMimeTypeMap() {
        Map<String, String> mimeTypeMap = new HashMap<>();
        mimeTypeMap.put("application/pdf", "pdf");
        mimeTypeMap.put("image/png", "png");
        mimeTypeMap.put("image/jpeg", "jpg");
        return mimeTypeMap;
    }

    public String convertPDFToImage(String filePath, String outputFilePath) {
        try {
            File sourceFile = new File(filePath);
            if (sourceFile.exists()) {
                PDDocument document = PDDocument.load(sourceFile);
                if (null != document) {
                    if (document.getNumberOfPages() > 1) {
                        throw new RuntimeException("Multi page pdf detected please upload one page pdf");
                    }
                }
                @SuppressWarnings("unchecked")
                PDPageTree pdPages = document.getDocumentCatalog().getPages();
                String fileName = sourceFile.getName().replace(".pdf", "");
                PDFRenderer pdfRenderer = new PDFRenderer(document);
                BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);

                ImageIOUtil.writeImage(bim, outputFilePath, 300);
                document.close();
                return outputFilePath;
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }


    public String decodeBase64Image(String base64image, String fileName, String filePath, String imageType) {
        try {
                String finalPath = filePath + "/" + fileName + "." + imageType;
                if (createRecurringDirectory(finalPath)) {
                    String[] imageSourceList = base64image.split(",");
                    String imageSource = "";
                    if (imageSourceList.length == 1) {
                        imageSource = imageSourceList[0];
                    } else {
                        imageSource = imageSourceList[1];
                    }
                    BufferedImage image = null;
                    byte[] imageByte;

                    BASE64Decoder decoder = new BASE64Decoder();
                    imageByte = decoder.decodeBuffer(imageSource);

                    ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                    image = ImageIO.read(bis);
                    bis.close();
                    File outputFile = new File(filePath);
                    ImageIO.write(image, imageType, outputFile);
                    saveBufferedImageToDestination(getBufferedImage(outputFile), imageType, outputFile);

                    return outputFile.getAbsolutePath();
                } else {
                    logger.error("Error while creating directory, " + finalPath);
                    return null;
                }
        } catch (Exception e) {
            logger.error("Error while saving base64 image, invalid base64 image, " + base64image, e);
            return null;
        }
    }

    public String getBase64StringFromFile(String filePath) {
        try {
            BASE64Encoder base64Encoder = new BASE64Encoder();
            logger.debug("File path to be enocded to base64, " + filePath);
            return base64Encoder.encode(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            logger.error("Error while encoding to base64 string, ", e);
            return null;
        }
    }

    private BufferedImage getBufferedImage(File name) throws IOException {
        return ImageIO.read(name);
    }

    private void saveBufferedImageToDestination(BufferedImage sourceBytes, String type, File dest) {
        try {
            ImageIO.write(sourceBytes, type, dest);
        } catch (IOException e) {
            logger.info(e.getMessage(), e);
        }
    }

    public boolean createDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        return directory.exists() || directory.mkdirs();
    }

    public boolean createRecurringDirectory(String fullpath) {
        String[] dirRecArray = fullpath.split("/");
        StringBuilder pathToCreate = new StringBuilder("/");
        int depthlevel = 1;
        do {
            String currDir = pathToCreate.append(dirRecArray[depthlevel] + "/").toString();
            boolean result = createDirectory(currDir);
            System.out.println("Directory created, " + currDir + " ? " + result);
            depthlevel++;
        } while (depthlevel < dirRecArray.length);
        return true;
    }

    public int getRandomNumbers() {
        int max = 50;
        int min = 6;
        Random random = new Random();
        //50 is the maximum and 6 is the minimum
        return random.nextInt((max - min) + 1) + min;
    }

    public String getRandomString(int length) {
        return stringUtility.randomAlphaNumeric(length);
    }

    public Map convertJsonStringToMap(String jsonString) {
        Map map = new HashMap();
        try {
            map = mapper.readValue(jsonString, new TypeReference<HashMap<String, String>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(" Error in parsing json string " + e);
        }
        return map;
    }

    public boolean saveMultipartFile2disk(String filePath, MultipartFile multipartFile) {
        File file = new File(filePath);
        try {
            multipartFile.transferTo(file);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean createEmptyFile(String filePath) {
        try {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
            f.createNewFile();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getOriginalFilename(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            return file.getOriginalFilename().replaceAll(" ", "-");
        } catch (Exception e) {
            return null;
        }
    }

    public String multipartToBase64(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            return encodeFileToBase64Binary(bytes);
        } catch (Exception e) {
            return null;
        }
    }

    private String encodeFileToBase64Binary(byte[] bytes) {
        try {
            byte[] encoded = org.apache.tomcat.util.codec.binary.Base64.encodeBase64(bytes);
            String encodedString = new String(encoded);
            return encodedString;
        } catch (Exception e) {
            return null;
        }
    }

    public String removeWhitespace(String input) {
        input = input.trim();
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("");
        return nowhitespace.toLowerCase(Locale.ENGLISH);
    }

    public String toSlug(String input) {
        input = input.trim();
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    public String generateRandomNames() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public Boolean deleteFile(String path) {
        try {
            File file = new File(path);
            return file.delete();

        } catch (Exception e) {
            return false;
        }
    }

    public String readFile(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            return null;
        }
    }

    public String objectToJsonString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException jpe) {
            logger.error("Error occurred while processing json, ", jpe);
            return null;
        }
    }

    public String objectToJsonStringwitDateFormat(Object object) {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            mapper.setDateFormat(df);
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException jpe) {
            logger.error("Error occurred while processing json, ", jpe);
            return null;
        }
    }

    public String convertDateToYYYYMMDDFormatString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public Date convertStringYYYYMMDDFormatToDate(String date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean createZip(String[] files, File zipfile) {
        try {
            FileOutputStream fos = new FileOutputStream(zipfile);
            ZipOutputStream zipOS = new ZipOutputStream(fos);
            for (String file : files) {
                if (stringNotEmpty(file)) {
                    writeToZipFile(file, zipOS);
                }
            }
            zipOS.close();
            fos.close();
            return true;

        } catch (Exception fnf) {
            return false;
        }
    }

    private void writeToZipFile(String path, ZipOutputStream zipStream) throws IOException {
        File aFile = new File(path);
        FileInputStream fis = new FileInputStream(aFile);
        ZipEntry zipEntry = new ZipEntry(path.substring(path.lastIndexOf("/") + 1));
        zipStream.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipStream.write(bytes, 0, length);
        }
        zipStream.closeEntry();
        fis.close();
    }

    public String convertMultiPagePDFToImage(String filePath,String outputFilePath){
        try{
            File sourceFile = new File(filePath);
            if (sourceFile.exists()) {
                PDDocument document = PDDocument.load(sourceFile);
                @SuppressWarnings("unchecked")
                PDPageTree pdPages = document.getDocumentCatalog().getPages();
                String fileName = sourceFile.getName().replace(".pdf", "");
                PDFRenderer pdfRenderer = new PDFRenderer(document);
                BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);
                ImageIOUtil.writeImage(bim, outputFilePath, 300);
                document.close();
                return outputFilePath;
            }else{return  null;}
        }catch (Exception e){
            return null;
        }
    }
}
