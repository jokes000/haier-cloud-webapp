INSERT INTO `haiercloud`.`TB_VM` (`board_width`, `cpu`, `ip`, `MAC`, `memory`, `name`, `os`, `password`, `status`) VALUES (5, '2', '192.168.1.254', '84:2b:2b:a9:92:00', 12, 'hadoop254', 'Ubuntu 12.04 LTS x64', '111111', 1);
INSERT INTO `haiercloud`.`TB_VM` (`board_width`, `cpu`, `ip`, `MAC`, `memory`, `name`, `os`, `password`, `status`) VALUES (5, '2', '192.168.1.253', '00:25:64:da:e2:af', 2, 'hadoop253', 'Ubuntu 12.04 LTS x64', '111111', 1);
INSERT INTO `haiercloud`.`TB_VM` (`board_width`, `cpu`, `ip`, `MAC`, `memory`, `name`, `os`, `password`, `status`) VALUES (5, '2', '192.168.1.252', '00:21:9b:0f:08:3f', 4, 'hadoop252', 'Ubuntu 12.04 LTS x64', '111111', 1);
INSERT INTO `haiercloud`.`TB_VM` (`board_width`, `cpu`, `ip`, `MAC`, `memory`, `name`, `os`, `password`, `status`) VALUES (5, '2', '192.168.1.251', '00:21:70:5a:b3:3d', 4, 'hadoop251', 'Ubuntu 12.04 LTS x64', '111111', 1);
INSERT INTO `haiercloud`.`TB_VM` (`board_width`, `cpu`, `ip`, `MAC`, `memory`, `name`, `os`, `password`, `status`) VALUES (5, '2', '192.168.1.250', '00:24:e8:09:8f:62', 2, 'hadoop250', 'Ubuntu 12.04 LTS x64', '111111', 1);
INSERT INTO `haiercloud`.`TB_VM` (`board_width`, `cpu`, `ip`, `MAC`, `memory`, `name`, `os`, `password`, `status`) VALUES (5, '2', '192.168.1.249', '00:1d:09:74:c6:2a', 3, 'hadoop249', 'Ubuntu 12.04 LTS x64', '111111', 1);
INSERT INTO `haiercloud`.`TB_VM` (`board_width`, `cpu`, `ip`, `MAC`, `memory`, `name`, `os`, `password`, `status`) VALUES (5, '2', '192.168.1.248', '00:1e:4f:58:a6:ca', 3, 'hadoop248', 'Ubuntu 12.04 LTS x64', '111111', 1);

INSERT INTO `haiercloud`.`TB_PERM` VALUES ('perms[admin:permission:view', '', '/admin/permission');
INSERT INTO `haiercloud`.`TB_PERM` VALUES ('perms[admin:permission:add', '', '/admin/permission/add');
INSERT INTO `haiercloud`.`TB_PERM` VALUES ('perms[admin:permission:delete', '', '/admin/permission/delete');
INSERT INTO `haiercloud`.`TB_PERM` VALUES ('perms[admin:view', '', '/admin');
INSERT INTO `haiercloud`.`TB_PERM` VALUES ('perms[admin:group:view', '', '/admin/group');
INSERT INTO `haiercloud`.`TB_PERM` VALUES ('perms[admin:group:add', '', '/admin/group/add');
INSERT INTO `haiercloud`.`TB_PERM` VALUES ('perms[admin:user:view', '', '/admin/user');
INSERT INTO `haiercloud`.`TB_PERM` VALUES ('perms[admin:user:add', '', '/admin/user/add');
INSERT INTO `haiercloud`.`TB_PERM` VALUES ('perms[admin:user:delete', '', '/admin/user/delete');