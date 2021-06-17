import json
import datetime
import random
import copy

##   Helper Functions
# Generates a new random checksum as a hex string
def gen_checksum():
    rand_int = random.randint(0,4294967295)
    return hex(rand_int)

# Generate Random Pass/Fail
def gen_result():
    if (random.getrandbits(1)):
        return "PASS"
    else:
        return "FAIL"

# Helper class for date/timestamp management
class DateTracker:
    def __init__(self):
        self.now = datetime.datetime.now()
        self.inc_date = self.now

    def add_day(self):
        self.inc_date += datetime.timedelta(days=1)

    # Prints date in ISO 8601 format
    def to_date_str(self):
        return self.inc_date.isoformat() 

##  Classes used to build out data
# Security rule class
class SecurityRule:
    def __init__(self, filename, ruleID, dateTracker):
        self.filename = filename
        self.ruleID = ruleID
        self.checksum = gen_checksum()

        dateTracker.add_day()
        self.modified = dateTracker.to_date_str()
        self.rule_text = ""  # leave blank for now

    def touch_rule(self, time):
        self.modified = time
        self.checksum = gen_checksum()

    def make_json(self):
        json_obj = {
            "checksum" : self.checksum,
            "file" : self.filename,
            "modified" : self.modified,
            "ruleID" : self.ruleID,
            "rule_text" : self.rule_text
        }
        return json_obj

# Design file class; contains details for a given file
class DesignFile:
    def __init__(self, dateTracker, file_path):
        self.checksum = gen_checksum()
        
        dateTracker.add_day()
        self.modified = dateTracker.to_date_str()
        self.path = file_path
        self.child_files = [] # only relevant for .f files

    def touch_file(self, time):
        self.modified = time
        self.checksum = gen_checksum()
    
    def make_json(self):
        json_obj = {
            "checksum" : self.checksum,
            "modified" : self.modified,
            "path" : self.path,
            "childFiles" : self.child_files
        }
        return json_obj

# Overall project core class 
class ProjectCore:
    def __init__(self, projectID, dateTracker):
        self.name = projectID
        self.checksum = gen_checksum()
        
        dateTracker.add_day()
        self.modified = dateTracker.to_date_str()
        self.design = [] # leave blank for now
        self.sec_rules = []

    def add_sec_rule(self, newRule, dateTracker):
        self.sec_rules.append(newRule)
        self.modify(dateTracker)

    def add_design_file(self, designFile, dateTracker):
        self.design.append(designFile)
        self.modify(dateTracker)
    
    # Mimic a modification of the project core
    def rand_modify(self, dataTracker):
        self.modify(dateTracker)       

        # Randomly select whether this change was from a design or rule change
        if(random.getrandbits(1)):
            # Modify a random rule
            rand_index = 0
            
            # Only get a random index if there's more than 1 rule
            if( len(self.sec_rules) > 1):
                # Get a random index
                rand_index = random.randint(0, len(self.sec_rules)-1)

            rand_rule = self.sec_rules[rand_index]
            rand_rule.touch_rule(self.modified)
        else:
            # Modify a random design file            
            rand_index = 0
        
            # Only get a random index if there's more than 1 design
            if( len(self.design) > 1):
                rand_index = random.randint(0, len(self.design)-1)
           
            rand_file = self.design[rand_index]
            rand_file.touch_file(self.modified)


    def modify(self, dateTracker):
        self.checksum = gen_checksum()
        dateTracker.add_day()
        self.modified = dateTracker.to_date_str()
        
    
    def has_sec_rule(self, sec_rule):
        for r in self.sec_rules:
            if r.ruleID == sec_rule:
                return True
        return False
 
    def make_json(self):
        json_rules = []
        json_design = []
        for r in self.sec_rules:
            json_rules.append(r.make_json())

        for d in self.design:
            json_design.append(d.make_json())
        
        json_obj = {
            "checksum" : self.checksum,
            "projectName" : self.name,
            "lastModified" : self.modified,
            "securityRules" : json_rules,
            "design" : json_design
        }
        return json_obj


# Overall sim core object
class SimCore:
    def __init__(self, testname, projectCore, dateTracker):
        self.testname = testname
        self.proj_name = projectCore.name 
        self.checksum = projectCore.checksum
        self.rule_results = []

        # Increment date
        dateTracker.add_day()
        self.simtime = dateTracker.to_date_str()


        # Simulates a random quantity and selection of security rules from the project
        num_rules = len(projectCore.sec_rules)
        rules_to_sim = []
        # Create essentially a buffer of rules to simulate
        rules_to_sim = projectCore.sec_rules[:]

        for i in range(0, random.randint(1, num_rules)):
            # Only proceed if there are rules available to simulate
            if ( len(rules_to_sim) > 0):
                # Get a random rule to simulate
                rand_rule = rules_to_sim[random.randint(0, len(rules_to_sim)-1)]            

                # Create random result
                ran_result = gen_result()

                # Add to result list
                self.rule_results.append( {"id": rand_rule.ruleID,
                                           "result": ran_result} )

                # Remove from list of simulated rules
                rules_to_sim.remove(rand_rule)
            


    def make_json(self):
        json_obj = {
            "checksum" : self.checksum,
            "projectName" : self.proj_name,
            "simStart" : self.simtime,
            "ruleResults" : self.rule_results 
        }
        return json_obj




##  MAIN  ##
project_revisions = []
simulations = []
project_name = "aes"
random_range = 2

# Create date tracker
dateTracker = DateTracker()

# Create a design with just 1 file
designFile = DesignFile(dateTracker, "/home/aes/aes.v")

# New project with 1 rule and add design file
myProject = ProjectCore(project_name, dateTracker)
newRule = SecurityRule("aes.as", "Rule0", dateTracker)
myProject.add_sec_rule(newRule, dateTracker)
myProject.add_design_file(designFile, dateTracker)

# Generate random number of project revisions
for i in range(0, random.randint(0, random_range)):
    project_revisions.append(copy.deepcopy(myProject))
    if(not myProject.has_sec_rule("Rule" + str(i))):
        newRule = SecurityRule("aes.as", "Rule" + str(i), dateTracker)
        myProject.add_sec_rule(newRule, dateTracker)
    else:
        myProject.rand_modify(dateTracker)

# Generate random number of simulations
# Project must contain at least one security rule
if( len(myProject.sec_rules) > 0):
    for i in range(0, random.randint(0, random_range)):
        simulations.append(SimCore("test" + str(i), myProject, dateTracker))

# Generate more revisions
for i in range(0, random.randint(0, random_range)):
    project_revisions.append(copy.deepcopy(myProject))
    if(not myProject.has_sec_rule("Rule" + str(i))):
        newRule = SecurityRule("aes.as", "Rule" + str(i), dateTracker)
        myProject.add_sec_rule(newRule, dateTracker)
    else:
        myProject.rand_modify(dateTracker)

# Generate more simulations
# Project must contain at least one security rule
if( len(myProject.sec_rules) > 0):
    for i in range(0, random.randint(0, random_range)):
        simulations.append(SimCore("test" + str(i), myProject, dateTracker))


# Create JSON for project revisions
project_revisions.append(myProject) # do this just for printing purposes
project_jsons = []
for proj in project_revisions:
    project_jsons.append(proj.make_json())

# Print JSON
print "projectCore JSONs: "
print (json.dumps(project_jsons, sort_keys=True, indent=4))

# Create JSON for sim core
sim_jsons = []
for sim in simulations:
    sim_jsons.append(sim.make_json())

print
print
print "simCore JSON: "
print (json.dumps(sim_jsons, sort_keys=True, indent=4)) 
