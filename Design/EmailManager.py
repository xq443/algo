from collections import defaultdict
import json


# Sample JSON input as a string
json_data = '''
{
    "users": [
        {"name": "A", "plan": "X", "begin_date": 0, "duration": 30},
        {"name": "B", "plan": "Y", "begin_date": 1, "duration": 15}
    ],
    "changes": [
        {"name": "A", "new_plan": "Z", "change_date" : 5},
        {"name": "B", "extension": 15, "change_date" : 3}
    ]
}
'''

# parse json data
data = json.loads(json_data)
users = data['users']
changes = data['changes']


class EmailManager:
    def __init__(self, users, changes= None):
        self.users = users
        self.changes = changes or [] # Sets to `changes` if it's provided, otherwise `[]`
        self.events = defaultdict(list)
        
    def add_event(self, day, message):
        self.events[day].append(message)
        
    def schedule_initial_emails(self):
        for user in users:
            name = user['name']
            plan = user['plan']
            begin_date = user['begin_date']
            duration = user['duration']
            
            # schedule the welcome email
            self.add_event(begin_date, f"[Welcome] {name}, subscribe in the plan {plan}")
            
            # schedule the upcoming expiration email
            self.add_event(begin_date + duration - 15, f"[Upcome expiration] {name}, subscrine in the plan {plan}")
            
            # schdule the expiration
            self.add_event(begin_date + duration, f"[Expired] {name}, subscrine in the plan {plan}")
            
            
    def apply_changes(self):
        for change in sorted(self.changes, key = lambda x : x['change_date']):
            name = change['name']
            
            change_date = change['change_date']
            
            if 'new_plan' in change:
                new_plan = change['new_plan']
                self.add_event(change_date, f"[Changed] {name}, subscrine in the plan {new_plan}")
                
                # retrive the original plan
                original_plan = next((user['plan'] for user in users if user['name'] == name), "default")
                
                # update the expiration and reminder emails in the new plan
                for day, messages in self.events.items():
                    self.events[day] = [
                        message.replace(f"subscrine in the plan {original_plan}", f"subscrine in the plan {new_plan}")  
                        if f"[Upcome expiration] {name}" in message or f"[Expired] {name}" in message else message
                        for message in messages
                    ]
                
            elif 'extension' in change:
                extension = change['extension']
                
                # retrieve the original plan, begin_date, and duraton
                original_plan = next((user['plan'] for user in users if user['name'] == name), "default")
                begin_date = next((user['begin_date'] for user in users if user['name'] == name), "default")
                duration = next((user['duration'] for user in users if user['name'] == name), "default")
                
                # remove the expiraton upcoming and expiration email
                for day, messages in self.events.items():
                    self.events[day] = [
                        message for message in messages
                        if not (f"[Upcome expiration] {name}" in message or f"[Expired] {name}" in message)
                    ]
                
                # add new event date to the event dict
                
                self.add_event(change_date, f"[Renewed] {name}, subscrine in the plan {original_plan}")
                
                new_expiration = begin_date + duration + extension
                
                self.add_event(new_expiration, f"[Expired] {name}, subscrine in the plan {original_plan}")
                self.add_event(new_expiration - 15, f"[Upcome expiration] {name}, subscrine in the plan {original_plan}")
                
            
    def print_schedule(self):
        for day in sorted(self.events.keys()):
                print(f"{day} : {self.events[day]}")
                
        
# Create an instance of SubscriptionManager
manager = EmailManager(users, changes)
manager.schedule_initial_emails()
manager.apply_changes()
manager.print_schedule()

        
        