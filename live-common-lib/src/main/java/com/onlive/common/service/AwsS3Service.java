package com.onlive.common.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AwsS3Service {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3Client amazonS3Client;
    
    //s3에 파일 저장
    public void uploadFile(MultipartFile mfile,String uploadFolderPath,String uploadFileId) {
        ObjectMetadata objectMetadata = new ObjectMetadata();       
        objectMetadata.setContentType(mfile.getContentType());
        objectMetadata.setContentLength(mfile.getSize());  
        try (InputStream inputStream = mfile.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(this.bucket+uploadFolderPath, uploadFileId, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("파일 업로드에 실패하였습니다."));
        }
    }
    //s3에 ffmpeg로 생성한 영상 저장
    public void uploadFile(File file,String uploadFolderPath,String fileId) {
        amazonS3Client.putObject(new PutObjectRequest(this.bucket+uploadFolderPath,fileId ,file).withCannedAcl(CannedAccessControlList.PublicRead));
    }
    
    //파일삭제
    public void fileDelete(String path,String key) {
        try {
            //Delete 객체 생성
            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(this.bucket+path, key);
            //Delete
            this.amazonS3Client.deleteObject(deleteObjectRequest);
            //System.out.println(String.format("[%s] deletion complete", key));
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }
}
