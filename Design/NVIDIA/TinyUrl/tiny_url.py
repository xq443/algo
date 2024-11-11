import random
import string
from datetime import datetime, timedelta

class TinyURL:
    def __init__(self):
        self.short_url_key_map = {}
        self.long_url_key_map = {}
        self.chars = string.ascii_letters + string.digits

    def encode(self, long_url, expiration_time=None):
        if long_url in self.long_url_key_map:
            return self.long_url_key_map[long_url]

        short_url = self.get_short_url()
        while short_url in self.short_url_key_map:
            short_url = self.get_short_url()

        if expiration_time is None:
            expiration_time = datetime.now() + timedelta(days=180)

        self.short_url_key_map[short_url] = {'long_url': long_url, 'expiration_time': expiration_time}
        self.long_url_key_map[long_url] = short_url
        return short_url

    def decode(self, short_url):
        url_entry = self.short_url_key_map.get(short_url)
        if url_entry and datetime.now() < url_entry['expiration_time']:
            return url_entry['long_url']
        return "Error: URL has expired or does not exist."

    def delete_url(self, short_url):
        url_entry = self.short_url_key_map.pop(short_url, None)
        if url_entry:
            self.long_url_key_map.pop(url_entry['long_url'], None)
            return "Short URL deleted successfully."
        return "Error: Short URL not found."

    def get_short_url(self):
        return "http://tinyurl.com/" + ''.join(random.choices(self.chars, k=6))

if __name__ == "__main__":
    tiny_url = TinyURL()

    # Test case 1: Default expiration time (6 months)
    short_url1 = tiny_url.encode("https://example.com/very/long/url")
    print("Short URL:", short_url1)
    print("Long URL:", tiny_url.decode(short_url1))

    # Test case 2: Custom expiration time
    custom_expiration = datetime.now() + timedelta(days=10)
    short_url2 = tiny_url.encode("https://example.com/another/long/url", custom_expiration)
    print("Short URL:", short_url2)
    print("Long URL:", tiny_url.decode(short_url2))

    # Test case 3: Deleting a short URL
    print(tiny_url.delete_url(short_url1))
    print(tiny_url.decode(short_url1))  # Should return an error message
