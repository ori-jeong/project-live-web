package com.onlive.common.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onlive.common.vo.UploadFileVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UploadFileService {
    private final AwsS3Service awsS3Service;
    
    //폴더경로생성
    private String getFolder() {
        //"yyyy-mm-dd" 년월일
        LocalDate  nowDate = LocalDate .now();
        DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "/"+ nowDate.format(dayFormat);
    }
    
    //파일 id, 경로 생성해 vo에 저장(aws에 파일 저장)
    public UploadFileVo uploadFolderPath(MultipartFile multipartFile) {

        UploadFileVo uploadFile = new UploadFileVo();
        
        String uploadFolderPath = getFolder();          //날짜경로 만들기
        uploadFile.setUploadPath(uploadFolderPath);     //폴더 경로 저장
        
        String fileName = multipartFile.getOriginalFilename();       //파일 이름 불러오기
        fileName = fileName.substring(fileName.lastIndexOf("\\")+1); //파일 이름 설정
        uploadFile.setFileName(fileName);                            //파일 이름 저장
            
        UUID uuid = UUID.randomUUID();                               //파일번호 생성
        uploadFile.setFileId(uuid.toString());                       //파일번호 저장
        fileName= uuid.toString() + "_" + fileName;                  //파일번호_파일이름
        
        awsS3Service.uploadFile(multipartFile, uploadFolderPath,uuid.toString()); //aws에 저장
        
        //ubuntu에 저장
//        String uploadFolder ="/mnt/img";
//        File uploadPath = new File(uploadFolder, uploadFolderPath); //폴더 경로 설정
//        if (uploadPath.exists() == false) {                         //해당 경로에 폴더 존재 확인
//            uploadPath.mkdirs();
//        }
//        File saveFile = new File(uploadPath, multipartFile.getOriginalFilename());
//        try {         
//            multipartFile.transferTo(saveFile); //파일 위치, 파일 이름을 합친 FILE 객체
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        
        //파일 내 컴퓨터에 저장 (aws 연결 후 주석처리)
        //String uploadFolder = "C:\\Users\\jh\\Desktop\\upload";     //파일 저장할 폴더 경로
//        String uploadFolder = "C:\\workspace\\onl-front-web\\src\\main\\resources\\static\\img\\upload";
//        File uploadPath = new File(uploadFolder, uploadFolderPath); //폴더 경로 설정
//        if (uploadPath.exists() == false) {                         //해당 경로에 폴더 존재 확인
//            uploadPath.mkdirs();
//        }
//        File saveFile = new File(uploadPath, multipartFile.getOriginalFilename());
//        try {         
//            multipartFile.transferTo(saveFile); //파일 위치, 파일 이름을 합친 FILE 객체
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        
        return uploadFile;
    }

}
