delete from eg_wf_matrix where objecttype='New Trade License' and
tenantId='default';

INSERT INTO eg_wf_matrix (id, department, objecttype, currentstate,
currentstatus, pendingactions, currentdesignation, additionalrule, nextstate,
nextaction, nextdesignation, nextstatus, validactions, fromqty, toqty,
fromdate, todate, tenantId) VALUES (NEXTVAL('SEQ_EG_WF_MATRIX'), 'ANY', 'New
Trade License', 'NEW', NULL, NULL, null, null, 'Acknowledged', 'Pending For
Application Fee', 'Junior Assistant', 'Acknowledged', NULL, NULL, NULL,
'2017-01-01', '2099-04-01', 'default');

INSERT INTO eg_wf_matrix (id, department, objecttype, currentstate,
currentstatus, pendingactions, currentdesignation, additionalrule, nextstate,
nextaction, nextdesignation, nextstatus, validactions, fromqty, toqty,
fromdate, todate,tenantId) VALUES (nextval('SEQ_EG_WF_MATRIX'), 'ANY', 'New
Trade License','Acknowledged', 'Acknowledged', NULL, 'Junior Assistant', NULL,
'Application Fee paid', 'Pending For
Application processing', 'Junior Assistant',
'Application Fee paid', 'Cancel', NULL, NULL, '2016-01-01',
'2099-04-01','default');

INSERT INTO eg_wf_matrix (id, department, objecttype, currentstate,
currentstatus, pendingactions, currentdesignation, additionalrule, nextstate,
nextaction, nextdesignation, nextstatus, validactions, fromqty, toqty,
fromdate, todate,tenantId) VALUES (nextval('SEQ_EG_WF_MATRIX'), 'ANY', 'New
Trade License','Application Fee paid', 'Application Fee paid', NULL, 'Junior
Assistant', NULL, 'Scrutiny Completed', 'SI/MHO/SS Approval Pending',
'Sanitary Inspector,Municipal Health Officer,Sanitary Supervisor', 'Scrutiny
Completed', 'Forward,Reject', NULL, NULL, '2016-01-01',
'2099-04-01','default');

INSERT INTO eg_wf_matrix (id, department, objecttype, currentstate,
currentstatus, pendingactions, currentdesignation, additionalrule, nextstate,
nextaction, nextdesignation, nextstatus, validactions, fromqty, toqty,
fromdate, todate,tenantId) VALUES (nextval('SEQ_EG_WF_MATRIX'), 'ANY', 'New
Trade License', 'Scrutiny Completed', NULL, NULL, 'Sanitary
Inspector,Municipal Health Officer,Sanitary Supervisor', NULL, 'Inspection
Completed', 'Commissioner Approval pending', 'Commissioner', 'Inspection
Completed', 'Forward,Reject', NULL, NULL, '2015-04-01',
'2099-04-01','default');


INSERT INTO eg_wf_matrix (id, department, objecttype, currentstate,
currentstatus, pendingactions, currentdesignation, additionalrule, nextstate,
nextaction, nextdesignation, nextstatus, validactions, fromqty, toqty,
fromdate, todate,tenantId) VALUES (nextval('SEQ_EG_WF_MATRIX'), 'ANY', 'New
Trade License', 'Inspection Completed', NULL, NULL, 'Commissioner',
NULL,'Final approval Completed', 'Pending For License Fee Collection', 'Junior
Assistant', 'License Fee Paid', 'Approve,Reject', NULL, NULL, '2016-01-01',
'2099-04-01','default');

INSERT INTO eg_wf_matrix (id, department, objecttype, currentstate,
currentstatus, pendingactions, currentdesignation, additionalrule, nextstate,
nextaction, nextdesignation, nextstatus, validactions, fromqty, toqty,
fromdate, todate,tenantId) VALUES (nextval('SEQ_EG_WF_MATRIX'), 'ANY', 'New
Trade License', 'Final approval Completed', 'License Fee Paid', NULL, 'Junior
Assistant', NULL,'License Fee Paid', 'Print Certificate Pending', 'Junior
Assistant', 'License Issued', NULL, NULL, NULL, '2016-01-01',
'2099-04-01','default');


INSERT INTO eg_wf_matrix (id, department, objecttype, currentstate,
currentstatus, pendingactions, currentdesignation, additionalrule, nextstate,
nextaction, nextdesignation, nextstatus, validactions, fromqty, toqty,
fromdate, todate,tenantId) VALUES (nextval('SEQ_EG_WF_MATRIX'), 'ANY', 'New
Trade License', 'License Fee Paid', NULL, NULL, 'Junior Assistant',
NULL,'License Issued', 'END', NULL, NULL, 'Print Certificate', NULL, NULL,
'2016-01-01', '2099-04-01','default');


INSERT INTO eg_wf_matrix (id, department, objecttype, currentstate,
currentstatus, pendingactions, currentdesignation, additionalrule, nextstate,
nextaction, nextdesignation, nextstatus, validactions, fromqty, toqty,
fromdate, todate, tenantId) VALUES (NEXTVAL('SEQ_EG_WF_MATRIX'), 'ANY', 'New
Trade License', 'Rejected', 'Rejected', NULL, 'Sanitary Inspector,Municipal
Health Officer,Sanitary Supervisor', null, 'Scrutiny Completed', 'SI/MHO/SS
Approval Pending', 'Sanitary Inspector,Municipal Health Officer,Sanitary
Supervisor', 'Scrutiny Completed', 'Forward,Cancel', NULL, NULL, '2017-01-01',
'2099-04-01', 'default');
