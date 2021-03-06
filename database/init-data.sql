INSERT INTO `haiercloud`.`TB_VM` (`board_width`, `cpu`, `ip`, `MAC`, `memory`, `name`, `os`, `password`, `status`) VALUES (5, '2', '192.168.1.254', '84:2b:2b:a9:92:00', 12, 'hadoop254.local', 'Ubuntu 12.04 LTS x64', '111111', 1);
INSERT INTO `haiercloud`.`TB_VM` (`board_width`, `cpu`, `ip`, `MAC`, `memory`, `name`, `os`, `password`, `status`) VALUES (5, '2', '192.168.1.253', '00:25:64:da:e2:af', 2, 'hadoop253.local', 'Ubuntu 12.04 LTS x64', '111111', 1);
INSERT INTO `haiercloud`.`TB_VM` (`board_width`, `cpu`, `ip`, `MAC`, `memory`, `name`, `os`, `password`, `status`) VALUES (5, '2', '192.168.1.252', '00:21:9b:0f:08:3f', 4, 'hadoop252.local', 'Ubuntu 12.04 LTS x64', '111111', 1);
INSERT INTO `haiercloud`.`TB_VM` (`board_width`, `cpu`, `ip`, `MAC`, `memory`, `name`, `os`, `password`, `status`) VALUES (5, '2', '192.168.1.251', '00:21:70:5a:b3:3d', 4, 'hadoop251.local', 'Ubuntu 12.04 LTS x64', '111111', 1);
INSERT INTO `haiercloud`.`TB_VM` (`board_width`, `cpu`, `ip`, `MAC`, `memory`, `name`, `os`, `password`, `status`) VALUES (5, '2', '192.168.1.250', '00:24:e8:09:8f:62', 2, 'hadoop250.local', 'Ubuntu 12.04 LTS x64', '111111', 1);
INSERT INTO `haiercloud`.`TB_VM` (`board_width`, `cpu`, `ip`, `MAC`, `memory`, `name`, `os`, `password`, `status`) VALUES (5, '2', '192.168.1.249', '00:1d:09:74:c6:2a', 3, 'hadoop249.local', 'Ubuntu 12.04 LTS x64', '111111', 1);
INSERT INTO `haiercloud`.`TB_VM` (`board_width`, `cpu`, `ip`, `MAC`, `memory`, `name`, `os`, `password`, `status`) VALUES (5, '2', '192.168.1.248', '00:1e:4f:58:a6:ca', 3, 'hadoop248.local', 'Ubuntu 12.04 LTS x64', '111111', 1);
INSERT INTO `haiercloud`.`TB_VM` (`board_width`, `cpu`, `ip`, `MAC`, `memory`, `name`, `os`, `password`, `status`) VALUES (5, '8', '192.168.1.247', '00:1e:4f:58:a6:ca', 4, 'hadoop247.local', 'Ubuntu 12.04 LTS x64', '111111', 1);


INSERT INTO `haiercloud`.`TB_CLUSTERMETA` (`id`, `name`) VALUES ('1', 'hadoop');
INSERT INTO `haiercloud`.`TB_CLUSTERMETA` (`id`, `name`) VALUES ('2', 'mongodb');
INSERT INTO `haiercloud`.`TB_NODEMETA` (`name`,`meta_id`) VALUES ('namenode', '1');
INSERT INTO `haiercloud`.`TB_NODEMETA` (`name`,`meta_id`) VALUES ('secondarynamenode', '1');
INSERT INTO `haiercloud`.`TB_NODEMETA` (`name`,`meta_id`) VALUES ('jobtracker', '1');
INSERT INTO `haiercloud`.`TB_NODEMETA` (`name`,`meta_id`) VALUES ('datanode', '1');
INSERT INTO `haiercloud`.`TB_NODEMETA` (`name`,`meta_id`) VALUES ('tasktracker', '1');
INSERT INTO `haiercloud`.`TB_NODEMETA` (`name`,`meta_id`) VALUES ('mongod', '2');
INSERT INTO `haiercloud`.`TB_NODEMETA` (`name`,`meta_id`) VALUES ('mongos', '2');
INSERT INTO `haiercloud`.`TB_NODEMETA` (`name`,`meta_id`) VALUES ('configserver', '2');

INSERT INTO `haiercloud`.`TB_PERM` VALUES ('perms[admin:permission:view', '', '/admin/permission');
INSERT INTO `haiercloud`.`TB_PERM` VALUES ('perms[admin:permission:add', '', '/admin/permission/add');
INSERT INTO `haiercloud`.`TB_PERM` VALUES ('perms[admin:permission:delete', '', '/admin/permission/delete');
INSERT INTO `haiercloud`.`TB_PERM` VALUES ('perms[admin:view', '', '/admin');
INSERT INTO `haiercloud`.`TB_PERM` VALUES ('perms[admin:group:view', '', '/admin/group');
INSERT INTO `haiercloud`.`TB_PERM` VALUES ('perms[admin:group:add', '', '/admin/group/add');
INSERT INTO `haiercloud`.`TB_PERM` VALUES ('perms[admin:user:view', '', '/admin/user');
INSERT INTO `haiercloud`.`TB_PERM` VALUES ('perms[admin:user:add', '', '/admin/user/add');
INSERT INTO `haiercloud`.`TB_PERM` VALUES ('perms[admin:user:delete', '', '/admin/user/delete');