var dat = {
	"egf.create": {
		"numCols": 12/3,
		"url": "/egf-masters/functions/_create",
		"tenantIdRequired": true,
		"idJsonPath": "functions[0].code",
		"objectName": "functions",
		"groups": [
			{
				"label": "egf.create.function.title",
				"name": "createfunction",
				"fields": [
						{
							"name": "name",
							"jsonPath": "functions[0].name",
							"label": "Name",
							"pattern": "",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "Please Enter valid Number",
							"patternErrMsg": ""
							
						},
						{
							"name": "code",
							"jsonPath": "functions[0].code",
							"label": "Code",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,250}$",
							"type": "text",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length is more than 250"
						},
						{
							"name": "level",
							"jsonPath": "functions[0].level",
							"label": "Level",
							"type": "text",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length is more than 250"
						},
					{
							"name": "isParent",
							"jsonPath": "functions[0].isParent",
							"label": "Is Parent",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"defaultValue":true,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						} ,
						 
            {
							"name": "parentId",
							"jsonPath": "functions[0].parentId.id",
							"label": "Parent",
							"pattern": "",
							 "type": "singleValueListMultiple",
           			 "url": "/egf-masters/functions/_search?sortBy=code|$..id|$..code,$..name,$..active",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},

						{
							"name": "active",
							"jsonPath": "functions[0].active",
							"label": "Active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"defaultValue":true,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		]
	},
	"wc.search": {
		"numCols": 12/3,
		"url": "/wcms/masters/function/_search",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "function",
		"groups": [
			{
				"label": "wc.search.function.title",
				"name": "searchfunction",
				"fields": [
						{
							"name": "name",
							"jsonPath": "sizeInMilimeter",
							"label": "wc.create.mm",
							"pattern": "",
							"type": "text",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},
						{
							"name": "Active",
							"jsonPath": "active",
							"label": "wc.create.active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		],
		"result": {
			"header": [{label: "wc.create.code"},{label: "wc.create.sizeInInch"},{label: "wc.create.sizeInMilimeter"}, {label: "wc.create.description"}, {label: "wc.create.active"}],
			"values": ["code", "sizeInInch", "sizeInMilimeter","description","active"],
			"resultPath": "functions",
			"rowClickUrlUpdate": "/update/wc/function/{id}",
			"rowClickUrlView": "/view/wc/function/{id}"
			}
	},
	"wc.view": {
		"numCols": 12/3,
		"url": "/wcms/masters/function/_search?id={id}",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "functions",
		"groups": [
			{
				"label": "wc.view.function.title",
				"name": "viewfunction",
				"fields": [
						{
							"name": "code",
							"jsonPath": "functions[0].code",
							"label": "wc.create.code",
							"pattern": "",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},
						{
							"name": "sizeInMilimeter",
							"jsonPath": "functions[0].sizeInMilimeter",
							"label": "wc.create.mm",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},
						{
							"name": "sizeInInch",
							"jsonPath": "functions[0].sizeInInch",
							"label": "wc.create.groups.connectionDetails.hscfunctionType",
							"pattern": "",
							"type": "text",
						},
						{
							"name": "description",
							"jsonPath": "functions[0].description",
							"label": "wc.create.description",
							"type": "text",
 						},
						{
							"name": "active",
							"jsonPath": "functions[0].active",
							"label": "wc.create.active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		]
	},
	"wc.update": {
		"numCols": 12/3,
		"searchUrl": "/wcms/masters/function/_search?id={id}",
		"url":"/wcms/masters/function/{function.code}/_update",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "function",
		"groups": [
			{
				"label": "wc.update.function.title",
				"name": "updatefunction",
				"fields": [
					{
						"name": "name",
						"jsonPath": "functions[0].sizeInMilimeter",
						"label": "wc.create.mm",
						"pattern": "",
						"type": "number",
						"isRequired": true,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": "",
						"depedants":[{
								"jsonPath":"function.sizeInInch",
								"type":"textField",
								"pattern":"`${getVal('function.sizeInMilimeter')!=''?getVal('function.sizeInMilimeter'):0} * 0.039370`",
								"rg":"",
								"isRequired": false,
								"requiredErrMsg": "",
								"patternErrMsg": ""
							}]
					},
					{
						"name": "sizeInInch",
						"jsonPath": "functions[0].sizeInInch",
						"label": "wc.create.groups.connectionDetails.hscfunctionType",
						"pattern": "",
						"type": "number",
						"isRequired": false,
						"isDisabled": true,
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
					{
						"name": "description",
						"jsonPath": "functions[0].description",
						"label": "wc.create.description",
						"pattern": "",
						"type": "text",
						"isRequired": false,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
					{
						"name": "Active",
						"jsonPath": "functions[0].active",
						"label": "wc.create.active",
						"pattern": "",
						"type": "checkbox",
						"isRequired": false,
						"isDisabled": false,
						"defaultValue":true,
						"requiredErrMsg": "",
						"patternErrMsg": ""
					}
				]
			}
		]
	}
}

export default dat;
