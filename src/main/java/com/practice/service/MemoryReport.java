package com.practice.service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

@Component
public class MemoryReport {
	
	@Scheduled(fixedRate = 10000)
	public void reportMemoryUsage() {
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
		MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
		long usedHeapMemory = heapMemoryUsage.getUsed()  / (1024 * 1024) ;
		long totalHeapMemory = heapMemoryUsage.getMax()  / (1024 * 1024) ;
		System.out.printf("Heap Memory: Used = %d MB, Total = %d Mb%n", usedHeapMemory, totalHeapMemory);
	}
}
