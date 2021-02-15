package python;

public class jdbc_py {
//	import requests
//	import pprint
//	import time
//	import sys
//	import io
//	import threading
//	import psycopg2
//	import json
//
//	sys.stdout = io.TextIOWrapper(sys.stdout.detach(), encoding = 'utf-8')
//	sys.stderr = io.TextIOWrapper(sys.stderr.detach(), encoding = 'utf-8')
//
//
//	def thread_run():
//	    print('==========CALL API=========== TIME:: ',time.ctime())
//
//	    jsonData = call_check()
//	    attributes = jsonData.get('attributes')
//
//	    conn = psycopg2.connect(
//	                            host='58.229.178.229',
//	                            dbname='kna',
//	                            user='kna',
//	                            password='kna123$%^',
//	                            port='5432'
//	                            ) # db에 접속
//
//	    cur = conn.cursor()
//	    query = "INSERT INTO dawjones_accounts "
//	    query+= "(data,reg_date,id,current_downloaded_amount, "
//	    query+= "max_allowed_concurrent_extracts, cnt_curr_ext, max_allowed_document_extracts, max_allowed_extracts, "
//	    query+= "name,products,tot_document_extracts,tot_extracts, "
//	    query+= "tot_subscriptions,tot_topics )"
//	    query+= "values "
//	    query+= "(%s,now(),%s,cast(%s as integer), "
//	    query+= "cast(%s as integer),cast(%s as integer),cast(%s as integer),cast(%s as integer), "
//	    query+=" %s,%s,cast(%s as integer),cast(%s as integer),"
//	    query+=" cast(%s as integer),cast(%s as integer) )"
//
//	    cur.execute(query,
//	                    (
//	                        json.dumps(jsonData),jsonData.get('id'),attributes.get('current_downloaded_amount'),
//	                        attributes.get('max_allowed_concurrent_extracts'),attributes.get('cnt_curr_ext'),attributes.get('max_allowed_document_extracts'),attributes.get('max_allowed_extracts'),
//	                        attributes.get('name'),attributes.get('products'),attributes.get('tot_document_extracts'),attributes.get('tot_extracts'),
//	                        attributes.get('tot_subscriptions'),attributes.get('tot_topics')
//	                    )
//	                )
//
//	    conn.commit()
//
//	    cur.close()
//	    conn.close()
//	    print("==========CLOSE CONNECTION===========")
//	    print("==========COMPLETE===========")
//	    # threading.Timer(5, thread_run).start()
//
//
//
//	def call_check():
//	    headers ={
//	                "Content-Type": "application/json",
//	                "user-key": "5OYQDNNSyAib11bI3zbspDhbyPjU7wSm"
//	               }
//
//	    url = "https://api.dowjones.com/alpha/accounts"
//	    response = requests.get(url, data="", headers=headers)
//
//	    return response.json().get('data')
//
//
//	thread_run()
}
