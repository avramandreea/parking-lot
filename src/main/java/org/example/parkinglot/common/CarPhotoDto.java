package org.example.parkinglot.common;

public class CarPhotoDto {

    private Long id;
    private String fileName;
    private String fileType;
    private byte[] fileContent;

    public CarPhotoDto(Long id) {
        this.id = id;
    }

    public CarPhotoDto(Long id, String filename, String fileType, byte[] fileContent) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileContent = fileContent;
    }

    public String getFileName() {
        return fileName;
    }

    public CarPhotoDto(String fileName) {
        this.fileName = fileName;
    }

    public Long getId() {
        return id;
    }

    public String getFileType() {
        return fileType;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public CarPhotoDto(byte[] fileContent) {
        this.fileContent = fileContent;
    }
}