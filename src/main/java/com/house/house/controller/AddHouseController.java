package com.house.house.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.house.house.entity.House;
import com.house.house.service.IHouserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class AddHouseController {

	private String dirPath = "C:/file/";
	private String dirContractsPath = "C:/file/contracts/";
	// 简介图片地址
	private String simplePath = "";
	// 详细图片地址
	private StringBuilder detailsPath = new StringBuilder();
	
	@Autowired
	private IHouserService service;

	@RequestMapping("/MultipleUpload")
	@ResponseBody
	public Map<String, Object> upload(@RequestParam("file") List<MultipartFile> file, HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String,Object>();
		String localPath="";
		if (!file.isEmpty() && file.size() > 0) {
			for (MultipartFile f : file) {
				localPath="";
				try {
					// 文件名
					String filename = UUID.randomUUID()
							+ f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf("."));
					// 存储虚拟路径
					 localPath ="http://localhost:8010/images/"+ filename;
					System.out.println(localPath);
					detailsPath.append(localPath+"~");

					File filePath = new File(dirPath);
					if (!filePath.exists()) {
						filePath.mkdirs();
					}
					//上传
					f.transferTo(new File(dirPath + filename));

				} catch (Exception e) {
					map.put("code", 1);
					map.put("msg", "上传失败");
					e.printStackTrace();
				}
			}
			map.put("code", 0);
			map.put("msg", "上传成功");
			map.put("url",localPath);
		}
		return map;
	}

	@RequestMapping("/singleUpload")
	@ResponseBody
	public Map<String, Object> singleUpload(@RequestParam("file") MultipartFile file, HttpServletRequest req,
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String filename = UUID.randomUUID() + suffixName;
			File filePath = new File(dirPath);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			//创建虚拟路径存储
			simplePath = "http://localhost:8010/images/"+ filename;//simplePath是个全局变量
			map.put("image", simplePath);
			file.transferTo(new File(dirPath + filename));
			map.put("code", 0);
			map.put("msg", "上传成功");
		} catch (Exception e) {
			map.put("code", 1);
			map.put("msg", "上传失败");
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 上传合同
	 * @param file
	 * @return
	 */
	@RequestMapping("/uploadContracts")
	@ResponseBody
	public Map<String,Object> uploadContracts(@RequestParam("file") MultipartFile file){
		Map<String, Object> map = new HashMap<>();

		try {
			String suffixName=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String filename=UUID.randomUUID()+suffixName;
			String fileVirtualPath="http://localhost:8010/Contracts/"+filename;
			File fileAddress = new File(dirContractsPath);
			if(!fileAddress.exists()){
				fileAddress.mkdirs();
			}
			file.transferTo(new File(dirContractsPath+filename));
			map.put("fileName",file.getOriginalFilename());
			map.put("file",fileVirtualPath);
			map.put("code", 200);
			map.put("msg", "上传成功");
		}catch (Exception e){
			map.put("code",500);
			map.put("msg","上传失败");
		}
		return map;
	}
	
	@RequestMapping("/addHouse")
	public String addHouse() {
		return "addhouse";
	}

	/**
	 * 添加房源
	 * @param house
	 * @return
	 */
	@RequestMapping  ("/addHouseRecord")
	@ResponseBody
	public String addHouse(@RequestBody House house) {
		System.out.println("real=>"+house.getHouseDetailsImg());
		if(house.getPublisher()==null||"".equals(house.getPublisher())) {
			house.setPublisher("管理员");
		}
		house.setHouseImage(simplePath);
		house.setHouseDetailsImg(house.getHouseDetailsImg());
		int n = service.addNewHouse(house);
		if(n>0) {
			detailsPath.delete(0,detailsPath.length());
			return "OK";
		}
		return "FAIL";
	}
}
