package com.gymbuddy.backgymbuddy.admin.banner.controller;

import com.gymbuddy.backgymbuddy.admin.banner.domain.Banner;
import com.gymbuddy.backgymbuddy.admin.banner.domain.BannerDto;
import com.gymbuddy.backgymbuddy.admin.banner.service.BannerService;
import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.ADMIN_BANNER_PREFIX;
import static com.gymbuddy.backgymbuddy.admin.base.Constants.BANNER_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BannerController extends BaseController {
    private static String OS = System.getProperty("os.name").toLowerCase();
    private String bannerPath = "/resources/images/banner";
private String basePath ="";
   if (isWindows()) {
	     basePath ="C:\\User";
	} else if (isMac()) {
	     basePath ="/home/";
	} else if (isUnix()) {
	     basePath ="/home/www";
	}
    private String rootPath = basePath + bannerPath;
    private File newFile = new File(rootPath);
    
	
    //폴더 없으면 경로 생성하기 
    if (!newFile.exists()) {
		try{
		    newFile.mkdir(); //폴더 생성합니다.
	    } 
	    catch(Exception e){
		    e.getStackTrace();
		 } 
    }
         private final BannerService bannerService;

    /**
     * 전체 배너 갯수 조회
     */
    @GetMapping(BANNER_PREFIX + "/totalCount")
    public ResponseEntity<Map<String, Object>> selectBannerTotalCount() {
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", bannerService.findAll().size());
        return createResponseEntity(true, result);
    }

    /**
     * 메인 배너 조회
     */
    @GetMapping(BANNER_PREFIX + "/all")
    public ResponseEntity<List<Banner>> selectMainBannerList() {
        return createResponseEntity(true, bannerService.findAll());
    }

    /**
     * 메인 베너 상세 조회
     */
    @GetMapping(BANNER_PREFIX + "/detail/{id}")
    public ResponseEntity<Banner> selectMainBannerDetail(@PathVariable("id") Long id) {
        log.info("메인 배너 아이디로 조회: {}", id);
        return createResponseEntity(true, bannerService.findOne(id));
    }

    /**
     * 메인 베너 등록
     */
    @PostMapping(ADMIN_BANNER_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertMainBanner(@ModelAttribute BannerDto banner) {
        log.info("메인 배너 등록: {}", banner);

        // 이미지 업로드
        String filename = banner.getFile().getOriginalFilename();
        try {
            File realFile = new File(newFile + "/" + System.currentTimeMillis() + "_" + filename);
            banner.getFile().transferTo(realFile);
            banner.setImgName(realFile.getName());
            banner.setImgPath(newFile + "/" + realFile.getName());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", bannerService.save(banner));
        return createResponseEntity(true, result);
    }

    /**
     * 메인 베너 수정
     */
    @PutMapping(ADMIN_BANNER_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateMainBanner(@PathVariable("id") Long id, @ModelAttribute BannerDto banner) {
        log.info("메인 배너 수정 - id: {}, banner: {}", id, banner);

        if (banner.getFile() != null) {
            String filename = banner.getFile().getOriginalFilename();
            try {
                File realFile = new File(newFile + "/" + System.currentTimeMillis() + "_" + filename);
                banner.getFile().transferTo(realFile);
                banner.setImgName(filename);
                banner.setImgPath(newFile + "/" + realFile.getName());

                // 이미지가 있는 경우 삭제
                Banner origin = bannerService.findOne(id);
                if (origin.getImgPath() != null) {
                    File originFile = new File(origin.getImgPath());
                    if (originFile.exists()) {
                        originFile.delete();
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        bannerService.update(id, banner);
        Banner findBanner = bannerService.findOne(id);

        boolean flag = true;
        if (banner.getTitle() != null) {
            flag = banner.getTitle().equals(findBanner.getTitle()) ? true : false;
        }
        if (banner.getCategoryId() != null) {
            flag = banner.getCategoryId().equals(findBanner.getCategoryId()) ? true : false;
        }
        if (banner.getLink() != null) {
            flag = banner.getLink().equals(findBanner.getLink()) ? true : false;
        }
        if (banner.getBtnTitle() != null) {
            flag = banner.getBtnTitle().equals(findBanner.getBtnTitle()) ? true : false;
        }
        if (banner.getImgPath() != null) {
            flag = banner.getImgPath().equals(findBanner.getImgPath()) ? true : false;
        }
        if (banner.getImgName() != null) {
            flag = banner.getImgName().equals(findBanner.getImgName()) ? true : false;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }

    /**
     * 메인 베너 삭제
     */
    @DeleteMapping(ADMIN_BANNER_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteMainBanner(@RequestBody List<Integer> ids) {
        log.info("메인 베너 삭제: {}", ids);

        for (int id : ids) {
            long idL = new Long(id);
            Banner origin = bannerService.findOne(idL);
            // 이미지 삭제
            if (origin.getImgPath() != null) {
                File originFile = new File(origin.getImgPath());
                if (originFile.exists()) {
                    originFile.delete();
                }
            }
            bannerService.delete(idL);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }
	
	public static boolean isWindows() {
  
        return (OS.indexOf("win") >= 0);
  
    }
  
    public static boolean isMac() {
  
        return (OS.indexOf("mac") >= 0);
  
    }
  
    public static boolean isUnix() {
  
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
  
    }
}
