package com.ruby.customandroiddemo.utils;

import java.io.File;

/**
 * 文件处理的工具类
 * @author Bob
 *
 */
public class FileUtils {

	/**
	 * 
	 * Deletes all files and subdirectories under "dir".
	 * 
	 * @param dir
	 *            Directory to be deleted
	 * 
	 * @return boolean Returns "true" if all deletions were successful.
	 * 
	 *         If a deletion fails, the method stops attempting to
	 * 
	 *         delete and returns "false".
	 */
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			if (children.length == 0) {
				return dir.delete();
			}
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// The directory is now empty so now it can be smoked
		return dir.delete();

	}
	
	public static boolean deleteDirChildren(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 获取文件或文件夹的大小
	 * 
	 * @param file 文件或文件夹
	 * @return  单位byte
	 */
	public static long getFileSize(File file) {
		long size = 0;
		File flist[] = file.listFiles();
		if (flist != null) {
			for (int i = 0; i < flist.length; i++) {
				if (flist[i].isDirectory()) {
					size = size + getFileSize(flist[i]);
				} else {
					size = size + flist[i].length();
				}
			}
		}
		return size;
	}
	
	public static boolean isFileExist(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}
}
