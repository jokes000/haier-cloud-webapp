package cn.edu.sjtu.se.dclab.haiercloud.web.monitor;

import java.util.List;

public class VMStatus {

	private String status;
	// private List<Metric> metrics;
	private Metric disk_free;
	private Metric mem_free;
	private Metric cpu_idle;
	private String name;
	private String ip;
	private String cpu;
	private String memory;
	private String storage;
	private String boardWidth;

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	private int warn;
	private int ok;

	public void getInfoFromGanglia(String info) {
		warn = 0;
		ok = 0;

		
		int index = info.indexOf("|");
		status = info.substring(0, index);
		
		if (status.equals("UNKNOWN")) {
			return;
		} else {
			info = info.substring(index + 1);
			System.out.println(info);
			
			index = info.indexOf("--");
			info = info.substring(index + 2);
			
			if (info.charAt(0) == 'W') {
				index = info.indexOf("--", index + 2);
				String warning = info.substring(0, index);
				System.out.println("warning is:" + warning);
				
				String[] fragments = warning.split(",");
				for (int i = 0; i < fragments.length; ++i) {
					Metric metric = new Metric();
					String[] tmp = fragments[i].split(" ");
					metric.setStatus(tmp[0]);
					metric.setName(tmp[1]);
					metric.setValue(tmp[3] + tmp[4]);
					if (metric.getName().equals("disk_free")) {
						disk_free = metric;
					} else if (metric.getName().equals("mem_free")) {
						mem_free = metric;
					} else {
						cpu_idle = metric;
					}
				}
				
				info = info.substring(index + 2);
			}
			
			String[] fragments = info.split(",");
			for (int i = 0; i < fragments.length; ++i) {
				Metric metric = new Metric();
				String[] tmp = fragments[i].split(" ");
				metric.setStatus(tmp[0]);
				metric.setName(tmp[1]);
				metric.setValue(tmp[3] + tmp[4]);
				if (metric.getName().equals("disk_free")) {
					disk_free = metric;
				} else if (metric.getName().equals("mem_free")) {
					mem_free = metric;
				} else {
					cpu_idle = metric;
				}
			}
		}
		
		/*
		System.out.println("info before split is:" + info);
		
		status = info.split("|")[0];
		info = info.split("|")[1];
		
		System.out.println("info after split is" + " " + info);

		int index = info.indexOf("WARN");
		if (index != -1) {
			int start = info.indexOf("(", index);
			int end = info.indexOf(")", index);

			warn = Integer.parseInt(info.substring(start + 1, end));
			System.out.println(warn);
		}

		if (warn > 0) {
			int start = info.indexOf("WARNING", 2);
			int end = info.indexOf("--", start);
			String warnStr = info.substring(start, end);

			String[] fragments = warnStr.split(",");
			for (int i = 0; i < fragments.length; ++i) {
				System.out.println(fragments[i]);
				Metric metric = new Metric();
				String[] tmp = fragments[i].split(" ");
				metric.setStatus(tmp[0]);
				metric.setName(tmp[1]);
				metric.setValue(tmp[3] + tmp[4]);
				if (metric.getName().equals("disk_free")) {
					disk_free = metric;
				} else if (metric.getName().equals("mem_free")) {
					mem_free = metric;
				} else {
					cpu_idle = metric;
				}
				// metrics.add(metric);
			}
			//info = info.substring(end + 1);
		}

		System.out.println("Before search for OK");
		index = info.indexOf("OK");
		System.out.println("OK is at " + index);
		
		if (index != -1) {
			int start = info.indexOf("(", index);
			int end = info.indexOf(")", index);

			ok = Integer.parseInt(info.substring(start + 1, end));
			System.out.println("OK is " + ok);
		}

		System.out.println("info is :" + info);
		if (ok > 0) {
			String[] temp = info.split("--");
			String okStr = temp[temp.length - 1];

			String[] fragments = okStr.split(",");
			for (int i = 0; i < fragments.length; ++i) {
				System.out.println(fragments[i]);
				Metric metric = new Metric();
				String[] tmp = fragments[i].split(" ");
				metric.setStatus(tmp[0]);
				metric.setName(tmp[1]);
				metric.setValue(tmp[3] + tmp[4]);
				if (metric.getName().equals("disk_free")) {
					disk_free = metric;
				} else if (metric.getName().equals("mem_free")) {
					mem_free = metric;
				} else {
					cpu_idle = metric;
				}
			}
		}
		*/
		
		//System.out.println(name + " " + ip + disk_free.getValue() + " " + mem_free.getValue() + " " + cpu_idle.getValue());
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Metric getDisk_free() {
		return disk_free;
	}

	public void setDisk_free(Metric disk_free) {
		this.disk_free = disk_free;
	}

	public Metric getMem_free() {
		return mem_free;
	}

	public void setMem_free(Metric mem_free) {
		this.mem_free = mem_free;
	}

	public Metric getCpu_idle() {
		return cpu_idle;
	}

	public void setCpu_idle(Metric cpu_idle) {
		this.cpu_idle = cpu_idle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBoardWidth() {
		return boardWidth;
	}

	public void setBoardWidth(String boardWidth) {
		this.boardWidth = boardWidth;
	}

}
