//cluster_id = cluster_id
//master_node = master_node
//server_ip = server_ip
//
//  -  "binlog_dir" :  1111
//  -  "cluster_id" :  1111
//  -  "master_node" : 1111
//  -  "user_group" :  1111
//  -  "server_ip" :  1111
//
//
//
//sed -i "s/^cluster_id = .*/cluster_id = .CLUSTER_ID/" ./agent.conf
//sed -i "s/^master_node = .*/master_node = .MASTER_NODE_INT/" ./agent.conf
//sed -i "s/^server_ip = .*/server_ip = .DIR_VIP/" ./agent.conf
//
//sed -i "s/\s*\"binlog_dir\" : .*/\"binlog_dir\" : \".BINLOG_DIR\"/" ./agent.conf
//sed -i "s/\s*\"cluster_id\" : .*/\"cluster_id\" : \".CLUSTER_ID\"/" ./agent.conf
//sed -i "s/\s*\"master_node\" : .*/\"master_node\" : \".MASTER_NODE_BOOLEAN\"/" ./agent.conf
//sed -i "s/\s*\"user_group\" : .*/\"user_group\" : \".USER_GROUP\"/" ./agent.conf
//sed -i "s/\s*\"server_ip\" : .*/\"server_ip\" : \".DIR_VIP\"/" ./agent.conf
